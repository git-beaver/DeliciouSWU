package team1.afinal.swu.deliciouswu;

import android.Manifest;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import team1.afinal.swu.deliciouswu.bean.ReviewBean;
import team1.afinal.swu.deliciouswu.util.Utils;

public class WriteReviewActivity extends AppCompatActivity {

    //사진이 저장된 경로 - onActivityResult()로부터 받는 데이터
    private Uri mCaptureUri;
    //사진이 저장된 단말기상의 실제경로
    private String mPhotoPath;
    //onActivityResult 에서 사용하는 구분값
    public static final int REQUEST_IMAGE_CAPTURE = 200;

    private ImageView imgPhoto;
    private EditText edtTitle, edtReview;
    public Button btnUpload;

    public EditText edtStore;

    private FirebaseAuth mAuth;
    private FirebaseStorage mStorage;
    private FirebaseDatabase mDatabase;

    private ReviewBean mReviewBean;

    private Spinner mSpinner;
    private String mStoreName = "가게 선택";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        mSpinner = findViewById(R.id.spinner2);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String[] storeArr = getResources().getStringArray(R.array.store);
                //Toast.makeText(WriteReviewActivity.this, storeArr[position], Toast.LENGTH_LONG).show();
                mStoreName= storeArr[position];

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        //카메라를 사용하기 위한 퍼미션을 요청한다.
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
        }, 0);

        mAuth = FirebaseAuth.getInstance();
        mStorage = FirebaseStorage.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        imgPhoto = findViewById(R.id.imgPhoto);
        edtTitle = findViewById(R.id.edtTitle);
        edtReview = findViewById(R.id.edtReview);
        btnUpload = findViewById(R.id.btnUpload);

        mReviewBean = (ReviewBean)
                getIntent().getSerializableExtra(ReviewBean.class.getName());

        if(mReviewBean != null){
            //수정
            try {
                new DownImgTask(imgPhoto).execute(new URL(mReviewBean.imgUrl));
            } catch (Exception e) {
                e.printStackTrace();
            }
            edtTitle.setText( mReviewBean.title );
            edtReview.setText( mReviewBean.contents );
            if(mReviewBean.store == null && mReviewBean.store == "") {
                mReviewBean.setRevStoreNum(0);
            }
            btnUpload.setText("후기 수정");
        }

        //이미지뷰 클릭시 카메라 기동
        imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        //업로드 클릭
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mReviewBean == null) {
                    //신규등록
                    upload(mCaptureUri);
                } else {
                    //수정처리
                    update(mCaptureUri);
                }

            }
        });

    }//end Create()


    /***************** 카메라 관련 Functions - Start *****************/
    private void takePicture() {

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mCaptureUri = Uri.fromFile( getOutPutMediaFile() );
        } else {
            mCaptureUri = FileProvider.getUriForFile(this,
                    "team1.afinal.swu.deliciouswu.fileprovider", getOutPutMediaFile());
        }

        i.putExtra(MediaStore.EXTRA_OUTPUT, mCaptureUri);

        //내가 원하는 액티비티로 이동하고, 그 액티비티가 종료되면 (finish되면)
        //다시금 나의 액티비티의 onActivityResult() 메서드가 호출되는 구조이다.
        //내가 어떤 데이터를 받고 싶을때 상대 액티비티를 호출해주고 그 액티비티에서
        //호출한 나의 액티비티로 데이터를 넘겨주는 구조이다. 이때 호출되는 메서드가
        //onActivityResult() 메서드 이다.
        startActivityForResult(i, REQUEST_IMAGE_CAPTURE);

    }

    private File getOutPutMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "cameraDemo");
        if(!mediaStorageDir.exists()) {
            if(!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File file = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");

        mPhotoPath = file.getAbsolutePath();

        return file;
    }

    private void sendPicture(String imgFilePath) {
        Bitmap bitmap = BitmapFactory.decodeFile(imgFilePath);
        Bitmap resizedBmp = getResizedBitmap(bitmap, 4, 100, 100);

        bitmap.recycle();

        //사진이 캡쳐되서 들어오면 뒤집어져 있다. 이애를 다시 원상복구 시킨다.
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imgFilePath);
        } catch(Exception e) {
            e.printStackTrace();
        }
        int exifOrientation;
        int exifDegree;
        if(exif != null) {
            exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            exifDegree = exifOrientToDegree(exifOrientation);
        } else {
            exifDegree = 0;
        }
        Bitmap rotatedBmp = roate(resizedBmp, exifDegree);
        imgPhoto.setImageBitmap( rotatedBmp );
    }

    private int exifOrientToDegree(int exifOrientation) {
        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        }
        else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        }
        else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

    private Bitmap roate(Bitmap bmp, float degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(),
                matrix, true);
    }

    //비트맵의 사이즈를 줄여준다.
    public static Bitmap getResizedBitmap(Bitmap srcBmp, int size, int width, int height){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = size;
        Bitmap resized = Bitmap.createScaledBitmap(srcBmp, width, height, true);
        return resized;
    }

    public static Bitmap getResizedBitmap(Resources resources, int id, int size, int width, int height){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = size;
        Bitmap src = BitmapFactory.decodeResource(resources, id, options);
        Bitmap resized = Bitmap.createScaledBitmap(src, width, height, true);
        return resized;
    }
    /***************** 카메라 관련 Functions - End *****************/


    /////////////////////////// Storage, Database Upload 관련 Function - Start ///////////////////////
    private void upload(final Uri fileUri) {
        if(fileUri == null) {
            Toast.makeText(this, "사진을 찍어주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        //image 파일을 파이어베이스 서버에 업로드 한다.
        //가장 먼저, FirebaseStorage 인스턴스를 생성한다.
        //Storage를 추가하면 상단에 gs:// 로 시작하는 스키마를 확인할 수 있다.
        FirebaseStorage fs = FirebaseStorage.getInstance("gs://swu-team1-finalproject.appspot.com");

        //위에서생성한 FirebaseStorage 를 참조하는 storage 를 생성한다.
        StorageReference storageRef = fs.getReference();

        //위의 저장소를 참조하는 images 폴더를 지정해서 이미지를 올린다.
        final StorageReference imageRef =
                storageRef.child("images/" + fileUri.getLastPathSegment());

        //다이얼로그 보이기
        Utils.showProgress(WriteReviewActivity.this);

        //실제파일 업로드 실행
        UploadTask uploadTask = imageRef.putFile(fileUri);

        //파일 업로드 성공/실패에 대한 콜백 메서드를 받아서 핸들링 한다.
        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if(!task.isSuccessful()) {
                    throw task.getException();
                }

                //이미지 파일이 올라간 실제 URL 주소를 리턴한다.
                //누구한테? addOnCompleteListener 에게
                return imageRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {

                //이미지 업로드가 끝나면 호출되는 CallBack 메서드
                //해야될일: Uploaded 된 이미지 URL 과 사용자가 작성한
                //메모의 내용을 RealTime DB 에 업로드 시킨다.

                DatabaseReference firebaseRef = mDatabase.getReference();
                String id = firebaseRef.push().getKey(); //KEY를 ID로 사용

                //Database 에 저장한다.
                ReviewBean bean = new ReviewBean();
                bean.id = id;
                bean.userId = mAuth.getCurrentUser().getEmail();
                bean.imgUrl = task.getResult().toString(); //이미지 다운로드 가능한 URL
                bean.imgName = fileUri.getLastPathSegment(); //파일이름도 저장
                bean.title = edtTitle.getText().toString();
                bean.contents = edtReview.getText().toString();
                bean.setRevStoreNum(mSpinner.getSelectedItemPosition());

                //bean.store = mSpinner.toString();
                //bean.store = edtStore.getText().toString();

                //userEmail 의 고유번호를 기준으로 사용자 데이터를 쌓기위해서
                //고유키를 생성한다.
                String userIdUUID = getUserIdFromUUID(bean.userId);

                firebaseRef.child("review").child(bean.id).setValue(bean);

                Toast.makeText(WriteReviewActivity.this,
                        "서버로 후기 글쓰기 성공!!", Toast.LENGTH_SHORT).show();

                //다이얼로그 보이기
                Utils.hideProgress(WriteReviewActivity.this);

                finish();
            }
        });

    }//end Upload()


    //수정처리
    private void update(final Uri fileUri){

        //사진을 찍지 않고 수정버튼을 눌렀을 경우는, fileUri 가 null 이온다.
        if(fileUri == null) {
            String emailUUID = getUserIdFromUUID( mReviewBean.userId );

            //수정할 데이터를 Bean 에 바꿔 넣어준다.
            mReviewBean.title = edtTitle.getText().toString();
            mReviewBean.contents = edtReview.getText().toString();
            mReviewBean.setRevStoreNum(mSpinner.getSelectedItemPosition());

            //서버 수정처리
            mDatabase.getReference().child("review").child(mReviewBean.id).setValue(mReviewBean);

            Toast.makeText(WriteReviewActivity.this,
                    "수정 되었습니다.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        //사진을 변경했을 때, 처리를 한다.
        //1.사진을 서버에 업로드 한다.
        //image 파일을 파이어베이스 서버에 업로드 한다.
        //가장 먼저, FirebaseStorage 인스턴스를 생성한다.
        //Storage를 추가하면 상단에 gs:// 로 시작하는 스키마를 확인할 수 있다.
        FirebaseStorage fs = FirebaseStorage.getInstance("gs://swu-team1-finalproject.appspot.com");

        //위에서생성한 FirebaseStorage 를 참조하는 storage 를 생성한다.
        StorageReference storageRef = fs.getReference();

        //위의 저장소를 참조하는 images 폴더를 지정해서 이미지를 올린다.
        final StorageReference imageRef =
                storageRef.child("images/" + fileUri.getLastPathSegment());

        //다이얼로그 보이기
        Utils.showProgress(WriteReviewActivity.this);

        //실제파일 업로드 실행
        UploadTask uploadTask = imageRef.putFile(fileUri);

        //파일 업로드 성공/실패에 대한 콜백 메서드를 받아서 핸들링 한다.
        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if(!task.isSuccessful()) {
                    throw task.getException();
                }

                //이미지 파일이 올라간 실제 URL 주소를 리턴한다.
                //누구한테? addOnCompleteListener 에게
                return imageRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {

                //새로운 이미지가 업로드가 끝나면 기존 이미지는 지워준다.
                mStorage.getReference().child("images")
                        .child(mReviewBean.imgName).delete();

                //이미지 업로드가 끝나면 호출되는 CallBack 메서드
                //해야될일: Uploaded 된 이미지 URL 과 사용자가 작성한
                //메모의 내용을 RealTime DB 에 업로드 시킨다.

                DatabaseReference firebaseRef = mDatabase.getReference();
                String id = firebaseRef.push().getKey(); //KEY를 ID로 사용

                //Database 에 저장한다.
                mReviewBean.imgUrl = task.getResult().toString(); //이미지 다운로드 가능한 URL
                mReviewBean.imgName = fileUri.getLastPathSegment(); //파일이름도 저장
                mReviewBean.title = edtTitle.getText().toString();
                mReviewBean.contents = edtReview.getText().toString();
                //mReviewBean.store = edtStore.getText().toString();

                //userEmail 의 고유번호를 기준으로 사용자 데이터를 쌓기위해서
                //고유키를 생성한다.
                String userIdUUID = getUserIdFromUUID(mReviewBean.userId);

                firebaseRef.child("review")
                        .child(mReviewBean.id).setValue(mReviewBean);

                Toast.makeText(WriteReviewActivity.this,
                        "서버로 메모 글수정 성공!!", Toast.LENGTH_SHORT).show();

                //다이얼로그 보이기
                Utils.hideProgress(WriteReviewActivity.this);

                finish();
            }
        });


    }//end update()


    //이메일의 문자 기준으로 고유번호를 뽑는다.
    public static String getUserIdFromUUID(String userEmail) {
        long val = UUID.nameUUIDFromBytes( userEmail.getBytes() ).getMostSignificantBits();
        return val + "";
    }
    /////////////////////////// Storage, Database Upload 관련 Function - End ///////////////////////


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //카메라로부터 오는 데이터를 취득한다.
        if(resultCode == RESULT_OK) {
            if(requestCode == REQUEST_IMAGE_CAPTURE) {
                sendPicture(mPhotoPath);
            }
        }

    }//end onActivityResult()
}

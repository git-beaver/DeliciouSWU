package team1.afinal.swu.deliciouswu;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

import team1.afinal.swu.deliciouswu.util.PrefUtil;
import team1.afinal.swu.deliciouswu.util.Utils;

public class LoginActivity extends AppCompatActivity {

    //파이어베이스 인증 객체 생성
    private FirebaseAuth firebaseAuth;

    // 비밀번호 정규식
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$");

    private Button btnLogin,btnJoin;
    private EditText edtId,edtPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //파이어베이스 인증 객체 선언
        firebaseAuth = FirebaseAuth.getInstance();

        btnLogin = findViewById(R.id.btnLogin);
        btnJoin = findViewById(R.id.btnJoin);
        edtId = findViewById(R.id.edtId);
        edtPw = findViewById(R.id.edtPw);

        //회원가입
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = edtId.getText().toString();
                final String pass = edtPw.getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("안내");
                builder.setMessage("회원 가입을 하시겠습니까?");
                builder.setCancelable(false); //밖깥영역 클릭시 다이얼로그 비표시 금지

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (isValidEmail(id) && isValidPasswd(pass)) {
                            //회원가입 시킨다 -> Firebase에
                            createUser(id, pass);
                        }
                    }
                });

                builder.create().show();
            }
        });

        //로그인
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString();
                String pass = edtPw.getText().toString();

                if (isValidEmail(id) && isValidPasswd(pass)) {
                    //로그인 하겠다
                    loginUser(id, pass);
                }
            }
        });

    }//end onCreate()

    //회원가입 처리
    private void createUser(String email, String pass) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            //회원가입 성공
                            Toast.makeText(LoginActivity.this, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show();
                            edtPw.setText("");
                        } else {
                            //회원가입 실패
                            Toast.makeText(LoginActivity.this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }  //end createUser()

    //로그인 처리
    private void loginUser(String email, String pass) {

        //다이얼로그 보이기
        Utils.showProgress(LoginActivity.this);

        firebaseAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this, "방문을 환영합니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            //로그인 실패
                            Toast.makeText(LoginActivity.this, "ID/PW 가 잘못 입력되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                        //다이얼로그 숨기기
                        Utils.hideProgress(LoginActivity.this);
                    }
                });
    }

    // 이메일 유효성 검사
    private boolean isValidEmail(String email) {
        if (email.isEmpty()) {
            // 이메일 공백
            Toast.makeText(this, "이메일 공백", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // 이메일 형식 불일치
            Toast.makeText(this, "이메일 형식 불일치", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    // 비밀번호 유효성 검사
    private boolean isValidPasswd(String password) {
        if (password.isEmpty()) {
            // 비밀번호 공백
            Toast.makeText(this, "비밀번호 공백", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            // 비밀번호 형식 불일치
            Toast.makeText(this, "비밀번호 형식 불일치", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }
}

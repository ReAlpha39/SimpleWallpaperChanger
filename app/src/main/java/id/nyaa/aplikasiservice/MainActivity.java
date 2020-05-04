package id.nyaa.aplikasiservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private Button mSetBtn;
    private Button mUnsetBtn;
    private RadioButton mMenitRadio;
    private RadioButton mLimaRadio;
    private RadioButton mTigaPuluhRadio;
    private RadioButton mJamRadio;
    private RadioGroup mTimeRadioGroup;
    public int mChangeTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSetBtn = findViewById(R.id.btnSet);
        mUnsetBtn = findViewById(R.id.btnUnset);
        mMenitRadio = findViewById(R.id.radio0);
        mLimaRadio = findViewById(R.id.radio1);
        mTigaPuluhRadio = findViewById(R.id.radio2);
        mJamRadio = findViewById(R.id.radio3);
        mTimeRadioGroup = findViewById(R.id.radioGroup);

        mUnsetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mDisable = new Intent(MainActivity.this, WallpaperChangeService.class);
                stopService(mDisable);
                finish();
            }
        });

        mSetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mRadioId = mTimeRadioGroup.getCheckedRadioButtonId();
                if (mMenitRadio.getId() == mRadioId) {
                    mChangeTime = 60;
                } else if (mLimaRadio.getId() == mRadioId) {
                    mChangeTime = 5 * 60;
                } else if (mTigaPuluhRadio.getId() == mRadioId) {
                    mChangeTime = 30 * 60;
                } else if (mJamRadio.getId() == mRadioId) {
                    mChangeTime = 60 * 60;
                }

                Intent mService = new Intent(MainActivity.this, WallpaperChangeService.class);

                Bundle mBundleTime = new Bundle();
                mBundleTime.putInt("durasi", mChangeTime);
                mService.putExtras(mBundleTime);
                startService(mService);
                finish();
            }
        });
    }
}

package top.fangwenmagician.myscan;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.alipay.android.phone.scancode.export.ScanRequest;
import com.alipay.android.phone.scancode.export.adapter.MPScan;
import com.alipay.mobile.antui.basic.AUToast;

public class MainActivity extends AppCompatActivity {

    private final ScanRequest scanRequest;
    private TextView scanTextView;

    public MainActivity() {
        scanRequest = new ScanRequest();
        scanRequest.setScanType(ScanRequest.ScanType.QRCODE);  // 二维码风格
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        scanTextView = findViewById(R.id.scan_text);
        // 按钮事件注册
        findViewById(R.id.open_scan).setOnClickListener(v -> openScan());
        findViewById(R.id.copy_to_clipboard).setOnClickListener(v -> copyToClipboard());
        findViewById(R.id.open_url).setOnClickListener(v -> openURL());
    }

    private void openScan() {
        MPScan.startMPaasScanActivity(MainActivity.this, scanRequest, (isProcessed, result) -> {
            if (!isProcessed) {
                // 扫码界面单击物理返回键或左上角返回键
                return;
            }
            // 注意：本回调是在子线程中执行
            runOnUiThread(() -> {
                if (result == null || result.getData() == null) {
                    // 扫码失败
                    AUToast.makeToast(this, com.alipay.mobile.antui.R.drawable.toast_false, "扫码失败，请重试", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 扫码成功
                scanTextView.setText(result.getData().toString());
            });
        });
    }

    private void copyToClipboard() {
        try {
            //获取剪贴板管理器
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(ClipData.newPlainText("二维码内容", scanTextView.getText()));
            AUToast.makeToast(this, com.alipay.mobile.antui.R.drawable.toast_ok, "复制成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            AUToast.makeToast(this, com.alipay.mobile.antui.R.drawable.toast_false, "复制到剪贴板出错", Toast.LENGTH_SHORT).show();
            Log.e(this.getClass().getName(), "复制到剪贴板出错", e);
        }
    }

    private void openURL(){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(scanTextView.getText().toString()));
            startActivity(intent);
        } catch (Exception e) {
            AUToast.makeToast(this, com.alipay.mobile.antui.R.drawable.toast_false, "打开浏览器出错", Toast.LENGTH_SHORT).show();
            Log.e(this.getClass().getName(), "打开浏览器出错", e);
        }
    }
}
package com.example.popic.ui.poem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.popic.R;
import com.example.popic.ui.modifyPoem.ModifyPoemActivity;
import com.example.popic.ui.picture.PictureActivity;
import com.example.popic.ui.uploadPicture.UploadPictureActivity;
import com.google.android.material.textview.MaterialTextView;

public class PoemActivity extends AppCompatActivity {
    Button modifyPoem, uploadPicture, seePicture;
    MaterialTextView title, context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_poem);

        String gettitle = intent.getExtras().getString("title");
        int get_edu_position = intent.getExtras().getInt("position_edu"); //n번째 에듀클래스
        Log.d("TAG", String.valueOf(get_edu_position));
        int get_poem_position = intent.getExtras().getInt("position_poem"); //n번째 시
        Log.d("TAG", String.valueOf(get_poem_position));
        title =  findViewById(R.id.poem_textview_name);
        context = findViewById(R.id.poem_textview_content);

        if(get_edu_position == 0){
            if(get_poem_position == 0){
                title.setText(gettitle);
                context.setText("님의 사랑은 강철을 녹이는 물보다도 뜨거운데, 님의 손길은 너무 차서 한도가 없습니다.\n" +
                        "나는 이 세상에서 서늘한 것도 보고 찬 것도 보았습니다. 그러나 님의 손길같이 찬 것은 볼 수가 없습니다.\n" +
                        "국화 핀 서리 아침에 떨어진 잎새를 울리고\n" +
                        "오는, 가을 바람도 님의 손길보다는 차지 못합니다. 달이 작고 별에 뽈나는 밤에, 얼음 위에 쌓인 눈도 님의 손길보다는 차지 못합니다.\n" +
                        "나의 작은 가슴에 타오르는 불꽃은\n" +
                        "님의 손길이 아니고는 끄는 수가 없습니다.\n" +
                        "\n" +
                        "님의 손길의 온도를 측량할만한 한란계는 나의 가슴 밖에는 아무데도 없습니다.\n" +
                        "님의 사랑은 불보다도 뜨거워서, 근심 산을 태우고 한 바다를 말리는데, 님의 손길은 너무도 차서 한도가 없습니다.\n");
            }else if(get_poem_position == 1){
                title.setText(gettitle);
                context.setText(
                        "그립다\n" +
                        "말을 할까\n" +
                        "하니 그리워\n" +
                        "\n" +
                        "그냥 갈까\n" +
                        "그래도\n" +
                        "다시 더 한번……\n" +
                        "\n" +
                        "저 산에도 까마귀, 들에 까마귀,\n" +
                        "서산에는 해 진다고\n" +
                        "지저귑니다.\n" +
                        "\n" +
                        "앞 강물, 뒷 강물,\n" +
                        "흐르는 물은\n" +
                        "어서 따라 오라고 따라 가자고\n" +
                        "흘러도 연달아 흐릅디다려.");

            }
        }else if(get_edu_position == 1){
            if(get_poem_position == 0){
                title.setText(gettitle);

                context.setText("가을날\n" +
                        "\n" +
                        "겹옷 사이로 스며드는 바람은\n" +
                        "산산한 기운을 머금고....\n" +
                        "드높아진 하늘은 비로 쓴 듯이 깨끗한\n" +
                        "맑고도 고용한 아침-\n" +
                        "\n" +
                        "예저기 흩어져 촉촉히 젖은\n" +
                        "낙엽을 소리없이 밟으며\n" +
                        "허리띠 같은 길을 내놓고\n" +
                        "풀밭에 들어 거닐어 보다\n" +
                        "\n" +
                        "끊일락 다시 이어지는 벌레 소리\n" +
                        "애연히 넘어가는 마디마디엔\n" +
                        "제철의 아픔이 깃들였다.\n" +
                        "\n" +
                        "곱게 물든 단풍 한 잎 따들고\n" +
                        "이슬에 젖은 치맛자락 휩싸쥐며 돌아서니\n" +
                        "머언 데 기차 소리가 맑다.");
            }
            else if (get_poem_position == 1){
                title.setText(gettitle);

                context.setText("꽃\n" +
                        "\n" +
                        "동방은 하늘도 다 끝나고\n" +
                        "비 한방울 나리잖는 그때에도\n" +
                        "오히려 꽃은 빨갛게 피지 않는가\n" +
                        "내 목숨을 꾸며 쉬임 없는 날이여\n" +
                        "\n" +
                        "북(北)쪽「쓴드라」에도 찬 새벽은\n" +
                        "눈속 깊이 꽃 맹아리가 옴자거려\n" +
                        "제비떼 까맣게 날라오길 기다리나니\n" +
                        "마침내 저바리지 못할 약속(約束)이며!\n" +
                        "\n" +
                        "한 바다복판 용솟음 치는 곳\n" +
                        "바람결 따라 타오르는 꽃성(城)에는\n" +
                        "나비처럼 취(醉)하는 회상(回想)의 무리들아\n" +
                        "오늘 내 여기서 너를 불러 보노라\n");
            }
        }

        modifyPoem = findViewById(R.id.poem_button_modifypoem);
        modifyPoem.setOnClickListener(v -> {
            startActivity(new Intent(this, ModifyPoemActivity.class));
        });

        uploadPicture = findViewById(R.id.poem_button_uploadpicture);
        uploadPicture.setOnClickListener(v -> {
            startActivity(new Intent(this, UploadPictureActivity.class));
        });

        seePicture = findViewById(R.id.poem_button_seepicture);
        seePicture.setOnClickListener(v -> {
            startActivity(new Intent(this, PictureActivity.class));
        });
    }
}
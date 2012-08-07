package com.my.utils.HowToUse;

import com.my.utils.AsyncImageLoader;
import com.my.utils.R;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.widget.ImageView;

public class AsyncImageLoaderHowToUse extends Activity {
        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.main);
                loadImage4("http://www.baidu.com/img/baidu_logo.gif", 0);
                loadImage4("http://www.chinatelecom.com.cn/images/logo_new.gif",
                                1);
                loadImage4("http://cache.soso.com/30d/img/web/logo.gif",
                                2);
                loadImage4("http://csdnimg.cn/www/images/csdnindex_logo.gif",
                                3);
                loadImage4("http://images.cnblogs.com/logo_small.gif",
                                4);
        }

        private AsyncImageLoader asyncImageLoader3 = new AsyncImageLoader();

        // 引入线程池，并引入内存缓存功能,并对外部调用封装了接口，简化调用过程
        private void loadImage4(final String url, final int id) {
            // 如果缓存过就会从缓存中取出图像，ImageCallback接口中方法也不会被执行
            Drawable cacheImage = asyncImageLoader3.loadDrawable(url,
                new AsyncImageLoader.ImageCallback() {
                    // 请参见实现：如果第一次加载url时下面方法会执行
                    public void imageLoaded(Drawable imageDrawable) {
                        ((ImageView) findViewById(id))
                                        .setImageDrawable(imageDrawable);
                    }
                });
            if (cacheImage != null) {
                ((ImageView) findViewById(id)).setImageDrawable(cacheImage);
            }
        }

}
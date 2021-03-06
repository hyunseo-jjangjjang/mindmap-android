package com.example.mindmap;

import android.util.Log;

public class TestCrawling {
    public Runnable callback;
    public String testWord = "";
    public class WordThread extends CrawlingThread{
        public void CompleteCrawling(){
            // 반대말, 하위어, 참고 어휘, 비슷한말, 낮춤말, 상위어, 본말/준말, 높임말
            testWord = getSimilarWords("비슷한말").toString();
            Log.d("비슷한말",testWord);
            callback.run();
        }
    }
    public TestCrawling(Runnable callback){
        this.callback = callback;
        WordThread thread = new WordThread();
        thread.start("감자");
    }
}

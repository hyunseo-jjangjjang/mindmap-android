package com.example.mindmap;

public class TestCrawling {
    public String testWord = "";
    public class WordThread extends CrawlingThread{
        public void CompleteCrawling(){
            // 반대말, 하위어, 참고 어휘, 비슷한말, 낮춤말, 상위어, 본말/준말, 높임말
            testWord = getSimilarWords().get("비슷한말").toString();
        }
    }
    public TestCrawling(){
        WordThread thread = new WordThread();
        thread.start("감자");
    }
}
# AResult
api 'com.linyuzai:aresult:1.0.3'
本项目以该项目为模板重写，去掉了Fragmentv4包，直接使用android.app.Fragment实现，避免与androidx冲突

仓库配置  
`maven { url 'https://jitpack.io' }`  
导入方式  
`api 'com.github.sc-hotpot:AResult:0.2'`  
使用方式    
````java    AResultUtil util = new AResultUtil(activity);
                        Intent intent = new Intent(activity, Activity.class);
                        util.startForResult(intent, new AResult.Callback() {
                            @Override
                            public void onActivityResult(AResultMessage result) {

                            }
                        });  


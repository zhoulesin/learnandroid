# 基于Dx的Android动态更新
https://mp.weixin.qq.com/s/N2gUDWHo6kEa2_mBH2vbtQ

## Dx简介
安卓程序的主要代码时Java代码，不过由于安卓系统不直接使用sun的jvm，所以从javac编译过来的class文
件并不能直接被安卓系统加载运行。

要想运行java代码，需要除了和以前一样调用javac将java代码编译为class文件外，还需要调用dx这个工具，
将class文件转化为dex文件。然后安卓系统才会去加载dex文件并执行程序。因此说，dx是将class文件转换
为dex的工具。

dx属于安卓开源项目的一部分，它的源码位于"external/dexmaker"目录下，纯java。由于是纯java工具，
所以可以打包成依赖库放到我们的项目里面，实现安卓平台上，将一组class文件或一个jar文件转换成dex
的功能。

## 使用dx
- 导入class文件
程序的第一步是将class文件以字节数组的方式读入内存。将运行时编译的class集合选择为MobTools，先将
它解压到项目的assets中。
```java
class A{
    private HashMap<String,byte[]> readAssets(String dir) throws Throwable{
        HashMap<String,byte[]> classes = new HashMap<>();
        String[] list = getAssets().list(dir);
        for (String name :list ) {
            String path = dir + "/" + name;
            if (path.endsWith(".class")){
                ByteArrayOutputStream baos=new ByteArrayOutputStream();
                InputStream is = getAssets().open(path);
                byte[] buf = new byte[1024];
                int len = is.read(buf);
                while(len > 0) {
                    baos.write(buf,0,len);
                    len = is.read(buf);
                }
                is.close();
                baos.close();
                classes.put(path,baos.toByteArray());
            }else{
                classes.putAll(readAssets(path));
            }
        }       
        return classes;
    }
}
```
- 初始化dex配置信息
载入class文件后，就可以将它们写入dex，但是dex会有一些配置信息需要初始化
```java
class A{
    public void aa(){
        DexOptions dexOptions = new DexOptions();
        dexOptions.targetApiLevel = DexFormat.API_NO_EXTENDED_OPCODES;
        
        CfOptions cfOptions = new CfOptions();
        cfOptions.positionInfo = PositionList.LINES;
        cfOptions.localInfo = true;
        cfOptions.strickNameCheck = true;
        cfOptions.optimize = false;
        cfOptions.optimizeListFile = null;
        cfOptions.dontOptimizeListFile = null;
        cfOptions.statistics = false;
    }
}
```
- 添加class文件
将class类名和数据添加到dex实例中
```java
class A{
    public void aa(){
        DexFile dex = new DexFile(dexOptions);
        ArrayList<String> classesNames = new ArrayList<>();
        HashMap<String,String> superClasses = new HashMap<>();
        HashMap<String,String[]> interfaces = new HashMap<>();
        for (Entry<String,byte[]> ent :classes.entrySet()){
            String classPath = ent.getKey();
            ClassDefItem item = CfTranslator.translate(classPath,ent.getValue(),
                cfOptions,dexOptions);
            dex.add(item);
            
            String className = classPath.replace("/",".");
            className = className.substring(0,className.length() - 6);
            classesNames.add(className);
            
            String superClass = item.getSuperClass().getDescriptor().getString().substring();
            superClass = superClass.substring(0,superClass.length()-1);
            if (classes.containsKey(superClass + ".class")){
                superClass = superClass.substring(0,superClass.length());
                
            }
        }
    }
}
```



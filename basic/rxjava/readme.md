# RxJava

## RxJava是什么

一个词:异步

## RxJava好在哪

一个词:简洁

## 引入

界面上有一个自定义视图iamgeCollectorView,他的作用是显示多张图片，并能使用addImage(Bitmap)方法来任意增加显示的图片，现在需要程序将一个给出的目录数组File[] folders中每个目录下的png图片都加载出来并显示在imageCollectorView上，需要注意的是，由于读取图片的这一过程较为耗时，而图片的显示必须在ui线程执行

```java
new Thread(){
    
    @override
    public void run(){
        super.run();
        for(File folder:folders){
            File[] files = folder.listFiles();
            for(File file:files){
                if(file.getName.endsWith(".png")){
				final Bitmap bitmap = getBitmapFromFile(file);
                    getActivity().runOnUiThread(new Runnable(){
                        
                        @override
                        public void run(){
                            imageCollectorView.addImage(bitmap);
                        }
                    })
                }
            }
        }
    }
}
```

而使用RxJava，实现方式是这样的

```java
Observable.from(folders)
    .flatMap(new Func1<File,Observable<File>>(){
		@override
        public Observable<File> call(File file){
            return Observable.from(file.listFile());
        }
    })
    .filter(new Func1<File,Boolean>(){
        @overrode
        public Boolean call(File file){
            return file.getName().endWith(".png");
        }
    })
    .map(new Func1<File,Bitmap>(){
        @override
        public Bitmap call(File file){
			return getBitmapFromFile(file);
        }
    })
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchcedulers.mainThread())
    .subscribe(new Action1<Bitmap>(){
        @override
        public void call(Bitmap bitmap){
            imageCollectorView.addImage(bitmap);
        }
    })
```

## API

### 观察者模式

RxJava的异步实现，是通过一种扩展的观察者模式来实现的

### RxJava中的Observable,Observer

RxJava有四个基本概念：Observable（可观察者，被观察者），Observer（观察者），subscribe（订阅），事件。。Observable和Observer通过subscribe（）方法实现订阅关系，从而Observable可以在需要的时候发出事件来通知Observer。

- Rxjava的事件回调方法除了普通事件onNext外，还有两个特殊的事件：onCompleted，onError

  - onCompleted：事件队列完结。Rxjava不仅把每个事件单独处理，还会把她们看做一个队列。Rxjava规定，当不会再有新的onNext发出时，需要出发onCompleted方法作为标志
  - onError：事件队列异常。在事件处理过程中出异常时，onError会被触发，同时队列自动终止，不允许再有事件发出.
  - 在一个正确运行的事件序列中，onCompleted和onError有且只有一个，并且时事件序列中的最后一个。需要注意的时，onCompleted和onError二者也是互斥的，即在队列中调用了其中要给，就不应该再调用另一个。

  RxJava观察者模式大致如下

  ![](../images/rxjava1.jpg)

- 基本实现

  - 创建Observer

    Observer即观察者，它决定事件触发的时候将有怎样的行为。RxJava中的Observer接口的实现方式:

    ```java
    Observer<String> observer = new Observer<String>(){
        @override
        public void onNext(String s){
            Log.d(tag,"Item:" + s);
        }
        @override
        public void onCompleted(){
            Log.d(tag,"Completed!");
        }
        @override
        public void onError(Throwable e){
            Log.d(tag,"Error!");
        }
    }
    ```

    除了Observer接口外，Rxjava还内置了一个实现了Observer的抽象类:Subscriber.Subscriber对Observer接口进行了一些扩展，但他们的基本使用方式是完全一样的:

    ```java
    Subscriber<String> subscriber = new Subscriber<String>(){
        @override
        public void onNext(String s){
            Log.d(tag,"Item:"+s);
        }
        @override
        public void onCompleted(){
            Log.d(tag,"Completed!");
        }
        @override
        public void onError(Throwable e){
            Log.d(tag,"Error!");
        }
    }
    ```

    不仅基本使用方式一样，实质上，在RxJava的subscribe过程中，Observer也总是会先被转换成一个Subscriber再使用,所以说你只想使用基本功能，选择Observer和Subscriber是完全一样的。他们的区别对于使用者主要有两点:

    ```text
    1.onStart():这是Subscriber增加的方法，他会在subscribe刚开始，而事件还未发送之前被调用，可以用于做一些准备工作，例如数据的清零或重置。这是一个可选方法，默认情况下他的实现为空。需要注意的是，如果对准备工作的线程有要求（例如弹出一个显示进度的对话框，着必须在主线程执行），onstart就不适用了，因为它总是在subscribe所发生的线程被调用，不能制定线程。要在制定的线程来做准备工作，可以使用doOnSubscribe方法
    2.unsubscribe（）：这是SUbscriber所实现的另一个接口subscription的方法，用于取消订阅。这个方法被调用后，subscriber将不再接受事件。一般在这个方法调用前，可以使用isunsubscribe先判断一下状态，unsubscribe这个方法很重要，因为在subscribe之后，Observable会持有Subscriber的引用，这个引用如果不能及时被释放，将有内存泄漏的风险。所以最好保持一个原则， 在不再使用的时候调用（如onPause，onStop）unSubscribe方法来解除引用关系，以避免内存泄漏的发生
    ```

  - 创建Observable

    Observable即被观察者，它决定什么时候触发事件以及触发怎样的事件。RxJava使用create方法来创建Observable,并且定义事件触发规则

    ```java
    Observable observable = Observable.create(new Observable.OnSubscribe<String>(){
        @Override
        public void call(Subscribe<? super String> subscriber){
        	subscriber.onNext("Hello");
            subscriber.onNext("Hi");
            subscriber.onNext("Aloha");
            subscriber.onCompleted();
    	}
    })
    ```

    可以看到，这里传入了一个OnSubscribe对象作为参数，OnSubscribe会被存储在返回的Observable对象中，它的作用相当与一个计划表，当Observable被订阅的时候，OnSubscribe的call方法会自动被调用，事件序列就会依照设定依次触发，（对于上面的代码，就是观察者Subscriber将会被调用三次onNext和一次onCompleted）。这样，由被观察者调用了观察者的回调方法，就实现了由被观察者向观察者的事件传递，即观察者模式。

    ```text
    这个例子很简单：事件的内容是字符串，而不是一些复杂的对象；事件的内容是已经定好了的，而不像有的观察者模式一样是待确定的（例如网络请求的结果在请求返回之前是未知的）；所有事件在一瞬间被全部发送出去，而不是夹杂一些确定或不确定的事件间隔胡总和经过某种触发器来触发的。总之，这个例子看起来毫无实用价值。但这是为了便于说明，实质上只要你想，各种各样的事件发送规则你都可以自己来写。
    ```

    create方法是RxJava最基本的创造事件序列的方法，基于这个方法，Rxjava还提供了一些方法来快捷创建事件队列.

    - just(T...) 将传入的参数依次发送出来

    ```java
    Observable observable = Observable.just("Hello","Hi","Aloha");
    //将会依次调用
    //onNext("Hello")
    //onNext("Hi")
    //onNext("Aloha")
    //onCompleted
    ```

    - from(T[])/from(Iterable<? extends T>):将传入的数组或Iterable拆分成具体对象后，依次发送出来

    ```java
    String[] words = {"hello","hi","aloha"};
    Observable observable= Observable.from(words);
    //将会依次调用
    //onNext("Hello")
    //onNext("Hi")
    //onNext("Aloha")
    //onCompleted
    ```

    上面just(T...)的例子和from(T[])的例子和之前的create(OnSubscribe)的例子是等价的.

  - Subscribe(订阅)

    创建了Observable和Observer之后，再用subscribe方法将他们联结起来，整条链子就可以工作了。

    ```java
    observable.subscribe(observer);
    observable.susbcribe(subscriber);
    ```

    ```text
    subscribe方法看起来是observable订阅了observer/subscriber，而不是observer/subscriber订阅了observable，着看起来就像杂志订阅了读者一样颠倒了对象关系。
    ```

    Observable.subscribe(Subscriber)的内部实现是这样的:

    ```java
    public Subscription subscribe(Subscriber subscriber){
        subscriber.onStart();
        onSubscribe.call(subscrber);
        return subscriber;
    }
    ```

    这里，subscriber做了三件事:

    ```text
    1.调用subscriber.onstart.
    2.调用Observable中OnSubscribe.call(Subscriber).在这里，事件发送的逻辑开始运行。从这也可以看出,在RxJava中，Observable并不是在创建的时候就发送事件，而是在被订阅的时候，即subscribe的时候。
    3.将传入的SUbscriber作为subscription返回，方便unSubscribe
    ```

  - 除了subscribe(Observer)和subscribe(Subscriber),subscribe还支持不完整那个定义的回调，Rxjava会自动根据定义创建出Subscriber

    ```java
    Action1<String> onNextAction = new Action1<String>(){
        @Override
        public void call(String s){
            Log.d(tag,s);
        }
    }
    Action1<Throwable> onErrorAction = new Action1<Throwable>(){
        @override
        public void call(Throwable throwable){
            
        }
    }
    Action0 onCompletedAction = new Action0(){
        @override
        public void call(){
            Log.d(tag,"completed");
        }
    }
    //自动创建Subscriber，并使用onNextAction来定义onNext
    observable.subscribe(onNextAction);
    //自动创建Subscriber,并使用onNextAction和onErrorAction来定义onNext和onError
    observable.subscribe(onNextAction,onErrorAction);
    //自动创建Subscriber,并使用onNextAtion,onErrorAction和onCompletedAction来定义onNext,onError,onCompleted
    observable.subscribe(onNextAction,onErrorAction,onCompletedAction);
    
    
    ```

  - 例子

    - 打印字符串数组

    ```java
    String[] names = ...;
    Observable.from(names)
        .subscribe(new Action1<String>(){
            @override
            public void call(String s){
                Log.d(tag,s);
            }
        });
    ```

    - 由id取得图片并显示

    ```java
    int drawableRes = ...;
    ImageView imageView = ...;
    Observable.create(new OnSubscribe<Drawable>(){
        @override
        public void call(Subscriber<? super Drawable> subscriber){
            Drawable drawable = getTheme().getDrawable(drawableRes);
            subscriber.onNext(drawable);
            subscriber.onCompleted();
        }
    }).subscribe(new Observer<Drawable>(){
       @override
        public void onNext(Drawable drawable){
            imageView.setImageDrawable(drawable);
        }
        
        @override
        public void onCompleted(){
            
        }
        
        @override
        public void onError(Throwable e ){
            Toast.makeText(activity,"Error!",Toast.LENGTH_SHORT).show();
        }
    });
    ```

    > ```text
    > 在RxJava默认规则中，事件的发出和消费都是在同一个线程的。也就是说，如果只用上面的方法，实现出来的只是一个同步的观察者模式，观察者模式本身的目的就是后台处理，前台回调的异步机制，因此异步对于Rxjava是至关重要的。而要实现异步，则需要用到RxJava的另一个概念：Scheduler
    > ```

### Scheduler 线程控制

在RxJava中，Scheduler调度器，相当于线程控制器，RxJava通过它来制定每一段代码应该运行在什么样的线程。

- Schedulers.immediate 直接在当前线程运行，相当于不制定线程
- Schedulers.newThread 总是启用新线程，并在新县城执行操作
- Schedulers.io() io操作，行为模式和newThread差不多，区别在于io颞部实现是用一个无数量上限的线程池，可以重用空闲的线程，因此，多数情况下io比newThread更有效率。
- Schedulers.computation:计算所使用的Scheduler.cpu密集型计算，不会被io等操作限制性能的操作，例如图形的计算，固定的线程池，大小为cpu核数。
- AndroidSchedulers.mainThread:Android主线程
- 使用subscribeOn 和observeOn两个方法来对线程进行控制
  - subscribeOn指定subscribe发生的线程,即Observable.OnSubscribe被激活时所处的线程。事件产生的线程
  - observeOn指定Subscriber所运行在的线程，或者叫做事件消费的线程

```java
Observable.just(1,2,3,4)
    .subscribOn(Schedules.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(new Action1<Integer>(){
        @override
        public void call(Integer number){
			Log.d(tag,"number:" + number);
        }
    })
```

上面这段代码中，由于subscribeOn(Schedulers.io())的制定，被创建的事件的内容1，2，3，4将会在io线程发出；而由于observeOn(AndroidSchedulers.mainThread())的制定，因此subscriber数字的打印发生在主线程。事实上，这种在subscribe之前写上两句subscribeOn(Scheduler.io())和observeOn(AndroidSchedulers.mainThread())的使用方式非常常见，它适用于多数的后台线程取数据，主线程显示 的程序策略.

前面的由id取图片的例子

```java
int drawable = ...;
ImageView imageView = ...;
Observable.create(new OnSubscribe<Drawable>(){
    @override
    public void call(Subscriber<? super Drawable> subscriber){
        Drawable drawable = getTheme().getDrawable(drawableRes);
        subscriber.onNext(drawable);
        subscriber.onCompleted();
    }
}).subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(new Observer<Drawabel>(){
        @override
        public void onNext(Drawable drawable){
            imageView.setImageDrawable(drawable);
        }
        
        @0verride
        public void onCompleted(){
            Log.d(tag,"Completed!")
        }
        
        @Override
        public void onError(Throwable e){
            Log.d(tag,"Error!")
        }
    })
```

### 变换

RxJava提供了对事件序列进行变化的支持，这是它的核心功能之一。所谓变换，就是将事件序列中的对象或整个序列进行加工处理，转换成不同的事件或事件序列。

- API

  - map：事件对象的直接变换，

  ```java
  Observable.just("images/logo.png")
      .map(new Func1<String,Bitmap>(){
          @Override
          public Bitmap call(String filePath){
              return getBitmapFromPath(filePath);
          }
      })
      .subscribe(new Action1<Bitmap>(){
          @Override
          public void call(Bitmap bitmap){
              showBitmap(bitmap);
          }
      })
  ```

  这里出现了一个叫做Func1的类，他和Action1非常相似，也是RxJava的一个接口，用于包装含有一个参数的方法，Func1和Action的区别在于，Func1包装的时候返回值的方法，另外，和ActionX用于，FuncX也有多个，用于不同参数个数的方法，FuncX和ActionX的区别在于FuncX包装的是有返回值的方法。

  可以看到，map方法将参数中的string对象转换成一个bitmap对象后返回，而在经过map方法后，事件的参数类型也由string转为了bitmap，这种直接变换对象并返回的，是最常见的也是最容易理解的变换，不过RxJava的变换远不止这样，它不仅可以针对事件对象，还可以针对整个事件队列，这使得RxJava非常灵活。

  - flatmap:现在需要打印一组学生的名字

  ```java
  Student[] students = ...;
  Subscriber<String> subscriber = new Subscriber<String>(){
      @Override
      public void onNext(String name){
  		Log.d(tag,name);
      }
      ...
  }
  Observable.from(students)
      .map(new Func1<Student,String>(){
        @Override
          public String call(Student student){
              return student.getName();
          }
      })
      .subscribe(subscriber);
  ```

  打印每个学生所需要修的所有课程的名称

  ```java
  Student[] students = ...;
  Subscriber<Student> subscriber = new Subscriber<Student>(){
      @Override
      public void onNext(Student student){
          List<Course> courses = student.getCourses();
          for(int i=0;i<courses.size();i++){
              Course course = courses.get(i);
              Log.d(tag,course.getName());
          }
      }
      ...
  }
  Observable.from(students)
      .subscribe(subscriber);
  ```

  不想在subscriber中使用for循环，而是希望subscriber中直接传入单个的course对象，显然map不行，因为map是一对一的转化，而我们要求的是一对多的转化

  ```java
  Student[] students = ...;
  Subscriber<Course> subscriber = new Subscriber<Course>(){
      @Override
      public void onNext(Course course){
          Log.d(tag,course.getName());
      }
      ...
  }
  Observable.from(students)
      .flatMap(new Func1<Student,Observable<Course>>(){
          @Override
          public Observable<Course> call(Student student){
  			return Observable.from(student.getCourses());
          }
      })
      .subscribe(subscriber);
  ```

  flatmap和map由一个相同点：他也是把传入的参数转化之后返回另一个对象。但和map不同的是，flatmap中但会的是个observable对象，并且这个observable对象并不是被直接发送到了subscriber的回调方法中，flatmap的原理：

  ```text
  1.使用传入的事件对象创建一个observable对象
  2.并不发送这个observable，而是将它激活，于是它开始发送事件
  3.每一个创建出来的observable发送的事件，都被汇入同一个observable，而这个observable负责将这些事件统一交给subscriber的回调方法。
  这三个步骤，把事件拆成了两级，通过一组新创建的observable将初始的对象铺平之后通过统一路劲分发了下去。这个铺平就是flatmap所谓的flat。
  ```

  由于可以在嵌套的observable中添加异步代码，flatmap也常用于嵌套的异步操作，例如嵌套的网络请求

  ```java
  networkClient.token()//返回Observable<String>,在订阅时请求token，并在响应后发送token
      .flatMap(new Func1<String,Observable<Messages>>(){
          @Override
          public Observable<Messages> call(String token){
              //返回Observable<Messages>,在订阅时请求消息列表，并在响应后发送请求到的消息列表
              return networkClient.messages();
          }
      })
      .subscribe(new Action1<Messages>(){
         @Override
          public void call(Messages messages){
              //处理显示消息列表
              showMessages(messages);
          }
      });
  
  ```

- 变换的原理 lift

  这些变化虽然功能各有不同，但实质上都是针对事件序列的处理和再发送。而在RxJava的内部，他们时基于同一个基础的变换方法:lift(Operator),

  ```java
  public <R> Observable<R> lift(Operator<? extends R,? super T> operator){
      return Observable.create(new OnSubscribe<R>(){
          @Override
          public void call(Subscriber subscriber){
  			Subscriber newSubscriber = operator.call(subscriber);
              newSubscriber.onStart();
              onSubscribe.call(newSubscriber);
          }
      })
  }
  ```

  这段代码生成一个新的Observable并返回，而且创建新Observable所用的参数OnSubscribe的回调方法call中的实现竟然看起来和前面的Observable.subscribe一样，然而第二行onSubscribe.call(subscriber)中的onSubscribe所指代的对象不同

  - subscribe中这句话的onSubscribe指的是Observable中的onSubscribe对象，这个没有问题，但是lift之后的情况就复杂了

  - 当含有lift时

    - lift创建了一个Observable后，加上之前的原始Observable,已经由两个Observable

    - 同样的，新Observable里的新OnSubscribe 加上之前的原始Observable中的原始OnSubscribe,也就有了两个OnSubscribe

    - 当用户调用经过lift后的Observable的subscribe时候，使用的是lift所返回的新Observable,于是它所触发的onSubscribe.call(subscriber),也是用的新Observable中的新OnSubscribe,即在lift中生成的OnSubscribe；

    - 而这个新OnSubscribe的call方法中的onSubscribe，就是指的原始Observable中的原始OnSubscribe，在这个call方法里，新OnSubscribe利用operator.call(subscriber)生成了一个新的

      Subscriber(Operator就是在这里，通过自己的call方法，将新Subscriber和原始Subscriber进行关联，并插入自己的变换代码以实现变换)，然后利用这个新Subscriber向原始Observable进行订阅

      这样就实现了lift过程，有点向代理机制，通过事件拦截和处理实现事件序列的变换.

    ```java
    observable.lift(new Observable.Operator<String,Integer>(){
        @Override
        public Subscriber<? super Integer> call(final Subscriber<? super String> subscriber){
            return new Subscriber<Integer>(){
                @Override
                public void onNext(Integer integer){
                    subscriber.onNext(""+integer);
                }
                @Override
                public void onCompleted(){
                    subscriber.onCompleted();
                }
                @Override
                public void onError(Throwable e){
                    subscriber.onError(e);
                }
            }
        }
    })
    ```

- compose:对Observable整体的变换

  除了lift之外，Observable还有一个变换方法叫做compose(Transformer).lift是针对事件项和事件序列的，而compose是针对observable自身进行变换。

  ```java
  observable1
  	.lift1()
      .lift2()
      .lift3()
      .lift4()
      .subscribe(subscriber1);
  observable2
  	.lift1()
      .lift2()
      .lift3()
      .lift4()
      .subscribe(subscriber2);
  observable3
  	.lift1()
      .lift2()
      .lift3()
      .lift4()
      .subscribe(subscriber3);
  ```

  改成这样

  ```java
  private Observable liftAll(Observable observable){
      return observable
          	.lift1()
          	.lift2()
          	.lift3()
          	.lift4();
  }
  ...
  liftAll(observable1).subscribe(subscriber1);
  liftAll(observable2).subscribe(subscriber2);
  liftAll(observable3).subscribe(subscriber3);
  ```

  通过compose解决

  ```java
  public class LiftAllTransformer implements Observable.Transformer <Integer,String>{
      @Override
      public Observable<String> call(Observable<Integer> observable){
          return observable.lift1().lift2()...;
      }
  }
  ...
  Transformer liftAll = new LiftAllTransformer();
  observable1.compose(liftAll).subscribe(subscriber1);
  observable2.compose(liftAll).subscribe(subscriber2);
  observable3.compose(liftAll).subscribe(subscriber3);
  ```

### 线程控制

- Schedule

  因为observeOn指的是subscribe的线程，而这个subscriber 并不是subscribe参数中的subscriber,而是observeOn执行时的当前observable所对应的subscriber,即它的直接下级subscriber.observeOn指的时它之后的操作所在的线程。因此如果有多次切换线程的需求，只要在每个想要切换线程的位置调用依次observeOn即可

  ```java
  Observable.just(1,2,3,4)
      .subscribeOn(Schedulers.io())
      .observeOn(Schedulers.newThread())
      .map(mapOperator)
      .observeOn(Schedulers.io())
      .map(mapOperator2)
      .observeOn(AndroidSchedulers.mainThread)
      .subscribe(subscriber);
  ```

  








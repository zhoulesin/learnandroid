# Retrofit Retrofit官网的介绍

http://square.github.io/retrofit/

## Introduction(引言)

Retrofit turns your HTTP API into a Java interface.

Retrofit将你的网络请求api变成了一个java接口

```java
public interface GitHubService{
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
```

The Retrofit class generates an implementation of the GitHubService interface.

Retrofit会为接口生成实现类

```java
Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("https://api.github.com/")
    .build();

GitHubService service = retrofit.create(GitHubService.class);
```

Each call from the created GitHubService can make a synchronous or asynchronous HTTP request to the remote webserver.

GitHubService创建的每个call都可以同步或异步的请求远程服务器

```java
Call<List<Repo>> repos = service.listRepos("octcat");
```

Use annotations to describe the HTTP request:

- URL parameter replacement and query parameter support
- Object conversion to request body(e.g.,JSON,protocol buffers)
- Multipart request body and file upload

利用注解来描述HTTP请求：

- url参数占位符，查询参数支持
- 数据转换支持(如：json转为Object)
- 文件上传支持



## API Declaration（API 声明)

Annotations on the interface methods and its parameters indicate how a request will be handled.

接口方法上面的注解和参数指明了一个网络请求会如何执行

### REQUEST METHOD(请求方式)

Every method must have an HTTP annotation that provides the request method and relative URL.There are five built-in annotations:GET,POST,PUT,DELETE and HEAD.The relative URL of the resource is specified in the annotation.

每个方法必须有一个提供了请求方式的http注解和url。我们内置了五种请求方式，GET，POST,PUT,DELETE和HEAD.这个url指定在注解内.

```java
@GET("user/list")
```

You can also specify query parameters in the URL

你也可以在url中制定查询参数

```java
@GET("users/list?sort=desc")
```

### URL MANIPULATION(URL 操作)

A request URL can be updated dynamically using replacement blocks and parameters on the method.A replacement block is an alphanumeric string surrounded by { and }.A corresponding parameter muse be annotated with @Path using the same string.

一个请求url可以通过使用**占位块**动态的更新，占位块需要用大括号包裹起来，对应的，我们要替换的参数必须使用path注解来表示，并且path注解的参数必须和占位块的名称相同。

```java
@GET("group/{id}/users")
Call<List<User>> groupList(@Path("id") int groupId)
```

Query parameters can also be added.

查询参数也可在方法中添加,使用query注解

```java
@GET("group/{id}/users")
Call<List<User>> groupList(@Path("id") int groupId,@Query("sort") String sort);
```

For complex query parameter combinations a Map can be used.

复杂(较多)的请求参数可以使用Map来实现

```java
@GET("group/{id}/users")
Call<List<User>> groupList(@Path("id") int groupId,@QueryMap Map<String,String> options);
```

### REQUEST BODY(请求体)

An object can be specified for use as an HTTP request body with the @Body annotation.

```java
@POST("users/new")
Call<Use> createUser(@Body User user);
```

The object will also be converted using a converter specified on the Retrofit instance .if no converter is added ,only RequestBody can be used.

### FROM ENCODED AND MULTIPART

Methods can also be declared to send form-encoded and multipart data.

Form-encoded data is sent when @FormUrlEncoded is present on the method.Each key-value pair is annotated with @Field containing the name and the object providing the value.

```java
@FormUrlEncoded
@POST("user/edit")
Call<User> updateUser(@Field("first_name") String first,@Field("last_name") String last);
```

Multipart requests are used when @Multipart is present on the method.Parts are declared using the @Part annotation

```java
@Multipart
@PUT("use/photo")
Call<User> updateUser(@Part("photo") RequestBody photo,@Part("description" RequestBody description));
```

Multipart parts use one of the Retrofit's converters or they can implement RequestBody to handle their own serialization.

### HEADER MANIPULATION

You can set static headers for a method using the @Headers annotation

```java
@Headers("Cache-Control:max-age=640000")
@GET("widget/list")
Call<List<Widget>> widgetList();
```

```java
@Headers({
    "Accept:application/vnd.github.v3.full+json",
    "User-Agent:Retrofit-Sample-App"
})
@GET("users/{username}")
Call<User> getUser(@Path("username") String username);
```

Note that headers do not overwrite each other.All headers with the same name will be included in the request.

A request Header can be updated dynamically using the @Header Annotation.A corresponding parameter must be provided to the @Header.If the value is null,the header will be omitted.Otherwise,toString will be called on the value, and the result used.

```java
@GET("user")
Call<User> getUser(@Header("Authorization") String authorization)
```

Similar to query parameters,for complex header combinations ,a Map can be used.

```java
@GET("user")
Call<User> getUser(@HeaderMap Map<String,String> headers)
```

Headers that need to be added to every request can be specified using an OkHttp interceptor.

如果每个请求都需要添加某一个Header，那么使用OkHttp拦截器来实现.

### SYNCHRONOUS VS. ASYNCHRONOUS

Call instances can be executed either synchronous or asynchronous.Each instance can only be used once,but calling clone() will create a new instance that can be used.

On Android,callbacks will be executed on the main thread.On the JVM,callbacks wil happen on the same thread that executed the HTTP request.



## Retrofit Configuration

Retrofit is the class through which your API interfaces are turned into callable objects.By default,Retrofit will give you sane defaults for your platform but it allows for customization.

Retrofit会提供默认的稳定的配置，但你也可以自定义.

### CONVERTERS

By default,Retrofit can only deserialize HTTP bodies into OkHttp's ResponseBody type and it can only accept its RequestBody type for @Body.

反序列化

Converters can be added to support other types.Six sibling modules adapt popular serialization libraries for your convenience.

- Gson
- Jackson
- Moshi
- Protobuf
- Wire
- Simple XML
- Scalars

Here's an example of using the GsonConverterFactory class to generate an implementation of the GitHubService interface which uses Gson for its deserialization.

```java
Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("https://api/github.com")
    .addConverterFactory(GsonConverterFactory.create())
    .build();

GitHubService service = retrofit.create(GitHubService.class);
```

### CUSTOM CONVERTERS

If you need to communicate with an API that uses a content-format that Retrofit does not support out of the box or you wish to use a different library to implement an existing format,you can easily create your own converter.

Create a class that extends the Converter.Factory class and pass in an instance when building your adapter.




# Class Finder
一个通过注解实现的查找子类工具。
使用场景在于Base的Lib中某些类需要依赖Base的业务方实现，而我们不希望增加额外的初始化方法的时候。
那么我们可以使用它。
只用的步骤：
1. Base Library 中声明需要实现的接口或者抽象类，或者普通类
2. Base Library 以常规方式引入 ClassFinder。
3. Base Library 中使用 ClassFinder 检索并且调用实现类。（返回结果将会以集合的形式提供，每个实例为 Class，且类型为设定的超类）
4. 使用的业务模块 Business Library 实现接口，或者继承超类。
5. Business Library 的依赖中，以 ksp 依赖 ClassFinder 模块。
6. 声明自己的业务注解，并以 @Volunteer 来注解，表示这是一个实现提供者注解。建议不同的业务声明为不同的业务注解。
7. 将自己声明的业务注解添加到实现类上，以业务注解来注解实现类。

具体细节，请参考项目案例：
- app 应用启动模块
- finder ksp 插件模块
- MockDemo 模拟 Base 模块，它表示最基础的接口声明
- ApplyDemo 接口实现模块

他们的依赖关系为：app - ApplyDemo - MockDemo
ClassFinder 会被 ApplyDemo 以 ksp 方式依赖，会在 ApplyDemo 中生成注解实现类（也就是找到具体接口实现子类并且收集）。
ClassFinder 会被 MockDemo 以 普通方式依赖，MockDemo 需要 ClassFinder 提供的接口来检索子类，因为子类会生成特定名称的类并被加载。
APP 无需依赖 ClassFinder，对于整个注解过程无感。

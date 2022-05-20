### Web应用建模

#### 功能需求建模

##### UML用例图
UML用例图由UML建模元素及元素之间的关系构成。本系统拥有用户、管理员和系统三个角色，分别可以进行如下操作：
![用例图](https://github.com/Zanejins/web-project/blob/main/application-modeling/img/use_case_diagram.png)

##### 活动图
活动图表达了用户从登录到完成合同签署的全部过程。
![活动图](https://github.com/Zanejins/web-project/blob/main/application-modeling/img/activity_diagram.png)

#### 内容建模

##### UML类图
UML类图是静态视图，是Web应用和领域有关的信息的规格说明，尽量忽略应用程序的导航、展示和交互等方面的内容，仅仅表达应用程序的概念框架。
以下类图由四个类之间的关系构成，其中User类和Administer类继承People类，分别表示两种系统使用者，Contract类存储合同信息。
![类图](https://github.com/Zanejins/web-project/blob/main/application-modeling/img/class-diagram.png)

##### 状态图
状态图是内容建模的动态视图，下图表示合同类的状态变化。
![状态图](https://github.com/Zanejins/web-project/blob/main/web_modeling/state_diagram.png)

#### 超文本建模

##### 超文本结构模型
超文本结构，即内容模型中包含的类和对象映射为超文本中节点（页面或文档）以及这些节点之间的链接，属于内容模型的上层视图。
![超文本结构模型](https://github.com/Zanejins/web-project/blob/main/application-modeling/img/hypertext-structure-model.png)

##### 超文本访问模型
访问模型描述了哪些节点可以通过导航方式来访问，以及如何通过导航访问到节点，建模导航路径。下图为用户签署合同时的访问路径。
![超文本访问模型](https://github.com/Zanejins/web-project/blob/main/application-modeling/img/hypertext-access-model.png)

#### 适应性建模

##### 适应性静态建模
根据用户上下文特性，给用户提供合适的展示。静态建模时，不同上下文使用不同的模型，改变时可能引起多种模型的变化以及可能的模型数量的增加。
![适应性静态模型](https://github.com/Zanejins/web-project/blob/main/application-modeling/img/adaptive-static-model.png)

##### 适应性动态模型
适应性动态建模由一个模型和适应性规则产生。下图表达了合同页面的动态适应，如果用户具有签合同的权限，那么显示签字界面进行签字，否则不提供签字界面。
![适应性动态模型](https://github.com/Zanejins/web-project/blob/main/web_modeling/dynamic_adaptive_modeling.png)

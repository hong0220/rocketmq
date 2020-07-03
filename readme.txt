编译源码:
mvn -Prelease-all -DskipTests clean install -U
在源码根目录distribution/target目录生成apache-rocketmq.tar.gz

启动测试示例:
bash tools.sh com.alibaba.rocketmq.example.quickstart.Producer
bash tools.sh com.alibaba.rocketmq.example.quickstart.Consumer

启动:
cd bin
./mqnamesrv
./mqbroker -n localhost:9876

修改jvm内存，-server -Xms512m，防止OOM。
runbroker.sh
runserver.sh

环境变量配置:
软链接
ln -s /path/rocketmq devenv
cd devenv
echo "ROCKETMQ_HOME=`pwd`" >> /etc/profile
echo "NAMESRV_ADDR=localhost:9876" >> /etc/profile
source /etc/profile

不同情况的提交：

1.checkout新项目，修改，提交
//在当前目录下建立solrLearning目录，并克隆成功solrLearning项目
$ git clone git://github.com/icersummer/solrLearning.git solrLearning
//如下命令checkin成功
$ git push https://github.com/icersummer/solrLearning.git master
Username for 'https://github.com':
Password for 'https://icersummer@github.com':
To https://github.com/icersummer/solrLearning.git
   dcfaaac..16d9c7e  master -> master

2.已存在的checkout的项目，拉取最新，修改，提交
git pull

3.建立分支，修改，提交

4.已存在分支，拉取最新，修改，提交

5.合并其他分支到Master分支
git merge <branch>

6.回滚本地未提交改动
git checkout -- <filename>

7.覆盖本地所有改动与提交
git fetch origin

8.删除文件，提交


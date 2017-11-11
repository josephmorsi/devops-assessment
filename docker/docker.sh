
#!/bin/bash
set -e

sudo yum update
sudo yum install apt-transport-https ca-certificates
#sudo apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D
sudo sh -c 'echo "deb https://apt.dockerproject.org/repo ubuntu-xenial main" > /etc/apt/sources.list.d/docker.list'
sudo yum update
sudo yum purge lxc-docker
#sudo apt-cache policy docker-engine
sudo yum install -y linux-image-extra-$(uname -r)
sudo yum install -y docker-engine
sudo service docker start
sudo systemctl enable docker
sudo groupadd docker
sudo usermod -aG docker $(whoami)

echo "Done. You should logout and login again so that you can run docker as your unprivileged user."

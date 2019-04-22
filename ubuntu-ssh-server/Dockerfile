FROM ubuntu:16.04
MAINTAINER Ahmed KHALED "https://github.com/ahmed24khaled"

RUN apt-get update && \
	apt-get install -y openssh-server && \
	mkdir /var/run/sshd && \
	echo 'root:root' |chpasswd && \
	sed -ri 's/^#?PermitRootLogin\s+.*/PermitRootLogin yes/' /etc/ssh/sshd_config && \
	sed -ri 's/UsePAM yes/#UsePAM yes/g' /etc/ssh/sshd_config && \
	mkdir /root/.ssh && \
	apt-get clean && \
	rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* 

EXPOSE 22
ENTRYPOINT service ssh start && bash
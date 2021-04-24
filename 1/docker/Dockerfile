FROM ubuntu:20.04

RUN apt update && apt install -y git curl autoconf bison build-essential \   
     libssl-dev libyaml-dev libreadline6-dev zlib1g-dev \   
     libncurses5-dev libffi-dev libgdbm6 libgdbm-dev libdb-dev

ADD  https://github.com/rbenv/rbenv-installer/raw/master/bin/rbenv-installer  \
    
RUN  echo 'export PATH="$HOME/.rbenv/bin:$PATH"' > ~/.bashrcecho 'eval "$(rbenv init -)"' > ~/.bashrcsource ~/.bashrc \

RUN  rbenv install 2.7.2 \
     rbenv global 2.7.2


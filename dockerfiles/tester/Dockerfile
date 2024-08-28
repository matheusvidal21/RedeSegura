FROM ubuntu:latest

RUN apt-get update && apt-get install -y \
    dnsutils \
    nmap \
    netcat-openbsd \
    snmp \
    smbclient \
    redis-tools \
    && rm -rf /var/lib/apt/lists/*

CMD ["sleep", "infinity"]

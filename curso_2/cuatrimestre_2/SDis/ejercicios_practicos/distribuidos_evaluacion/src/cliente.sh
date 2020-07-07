 
#!/bin/bash

java protocol.cliente.Hello localhost "Pedro"
java protocol.cliente.Push localhost 010 abc
java protocol.cliente.PullNoWait localhost 112
java protocol.cliente.PullNoWait localhost 010
java protocol.cliente.PullWait localhost 010

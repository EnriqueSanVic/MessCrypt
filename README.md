# MessCrypt

Aplicación Chat por grupos GUI servidor-cliente Online, en LAN y localhost.

Para probar ejecutar el Servidor.jar (servidor) en un dispositivo. Puerto por defecto: 4449. 

Después ejecutar Chat.jar (cliente) en las siguientes situaciones:

- localhost: Si Chat.jar se va a ejecutar en el mismo dispositivo donde se ha ejecutado el servidor entonces rellenar el campo Host del formulario de inicio de la aplicación con la cadena "loclhost".

- LAN: Si Chat.jar se va a ejecutar en la misma red local entonces rellenar el campo Host del formulario de inicio de la aplicación con la IP privada del dispositivo en el que se ha ejecutado el servidor en la red.

- Online: Si Chat.jar se va a ejecutar en un dispositivo/servidor de internet entonces rellenar el campo Host del formulario de inicio de la aplicación con la IP pública del servidor fisico en el que se ha ejecutado el servidor en la red, si este servidor tiene un dominio de internet, se puede poner el dominio sin problema, el protocolo DNS ya se encargará de encontrar la IP pública vinculada al dominio.

En culaquier caso el puerto por defecto es el 4449, se puede cambiar si se desea, pero hay que manipular el codigo fuente del servidor.

Una vez iniciado el Chat se pueden conectar mñultiples instancias de Chat.jar a distintos grupos para hablar con las instancias que estan conectadas al mismo grupo.

No hay límite de grupos ni de instancias conectadas a un mismo grupo.

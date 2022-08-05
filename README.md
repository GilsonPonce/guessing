# guessing
INTRODUCCION
En el juego llamado “20 preguntas”, un jugador piensa en una persona, animal o cosa, y otra persona intenta 
adivinar el objeto pensado haciendo máximo veinte preguntas.
En este proyecto, usted implementará una versión modificada de este juego:
▪ Al inicio de una partida, el programa solicitará al jugador pensar en un animal. 
▪ Luego, la computadora intentará adivinar el animal que el jugador pensó haciendo hasta N preguntas, 
cuyas respuestas pueden ser sí o no. El valor de N es ingresado por el jugador al inicio de la partida.
▪ El jugador deberá responder, a través de la interfaz, a cada pregunta hecha por la computadora.
▪ Cuando la computadora agote el número de preguntas permitidas:
o Si está segura de haber adivinado, deberá indicar al usuario su respuesta.
o Si en cambio no pudo llegar a una conclusión (por ejemplo, porque el número de preguntas 
que hizo fue muy pequeño), deberá indicar la lista de posibles animales en que podría haber 
pensado el jugador.
o Por otro lado, si no existe ningún animal que coincida con las respuestas provistas por el 
jugador, el programa debe notificar al usuario mediante un mensaje apropiado.
▪ Las preguntas y las posibles respuestas se proporcionarán al programa a través de archivos de texto
que deben ser cargados por el usuario al ejecutar el programa.

MODELAMIENTO DEL PROBLEMA
Como probablemente lo habrá notado, la aplicación descrita puede ser modelada con un árbol binario en que 
los sub-árboles de cada nodo puede representar las siguientes preguntas a hacer dependiendo de si la 
respuesta a la pregunta de la raíz es afirmativa o negativa. 
Este tipo de árboles se conocen como “árboles de decisión” y son utilizados, por ejemplo, en sistemas de 
diagnóstico médico, donde las respuestas a una serie de preguntas derivan en un diagnóstico en particular. 
El árbol generado a partir de los archivos preguntas.txt y respuestas.txt de la sección anterior luce así:
Este árbol es el que guiará a la computadora sobre qué preguntas hacer y sobre qué respuesta o posibles 
respuestas proponer al jugador. 
Asimismo, el árbol indicará a la computadora en qué casos no hay animales que coincidan con las respuestas 
del jugador. Por ejemplo, si un jugador responde sí a la primera pregunta, y no a las segunda y tercera 
preguntas, la computadora debe saber que no hay (en los archivos provistos) ningún animal que coincida con 
esas respuestas. Esta situación se ilustra en el árbol mostrado a continuación:
El camino azul de este árbol muestra una sucesión de respuestas que no derivan en ningún animal que exista 
en el archivo respuestas.txt. En este caso, la computadora debería indicar al jugador que no es posible 
encontrar un animal con estas características (o un mensaje similar)

# Prueba Técnica

1. He utilizado listas y hashmap para de estructuras. He usado listas en las estructuras que no me merecía la pena entrar 
de forma aleatoria, y HashMap en los objetos que si tenia su identificador y podía entrar para no tener que recorrer la lista. Esto lo hice así porque habían 
objetos que no podía acceder de forma directa y tenía que recorrerlos igualmente.
2. En notación O supongo que será O*productos*2*size, más todos los accesos aleatorios que tiene. He decidio hacerlo así ya que 
no he visto forma de filtrar mejor el size, solo por productos cada vez que entraba para coger los que necesitaba.
Además hago una ordenación de los productos y por eso lo he puesto *2. Quizás si se relacionan las cosas
con una base de datos o usando algún tipo de almacenamiento intermedio podría ser capaz de bajar
algo la complejidad, ya que podría seleccionar los conjuntos de datos de otra forma y no tenerlos
todos en memoría.

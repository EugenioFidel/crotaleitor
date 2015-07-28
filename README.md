# crotaleitor
Comprobador de numeros de crotal de ganado bovino en España

Los animales de la especie bovina en España están identificados mediante un crotal de material
plastico con un texto compuesto por dos letras ES que identifican el pais, un cero, un dígito
de control (cuyo calculo y control realiza este programa), dos dígitos que identifican la CA
en la que se da de alta el animal, cuatro dígitos que identifican un número de serie y cuatro
dígitos para cada animal.
El dígito de control se genera realizando un algóritmo sobre el resto de caracteres del número
de crotal y permite validar su escritura informado al usuario de que el número de crotal está
bien escrito

Este programa permite dos validaciones. 
1ª .- dado el número completo nos informa de si éste ha sido correctamente escrito
2ª .- Dados el numero de serie y el de crotal, así como los dos dígitos que identifican la
Comunidad autónoma nos permite calcular el número completo del crotal (incluyendo su DC)

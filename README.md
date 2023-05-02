# StoreForKids
This code is a program wich can carry out a raffle in a kid's store. Basic methods only.

Программа на Java "Магазин Игрушек".  
Программа имитирует розыгрыш игрушек: случайным образом выбирается игрушка и выдается покупателю.

В программе реализован следующий функционал:  
+ добавление игрушек в магазин
+ просмотр списка игрушек
+ изменение весового коэффициента (применяется в процессе розыгрыша игрушек)
+ проведение розыгрыша игрушек
+ выдача игрушки, определенной в процессе розыгрыша, покупателю
+ завершение программы.

### Розыгрыш игрушек.
В розыгрыше используются только те игрушки, которые внесены в специальный список.
Из этого списка случайным образом выбирается игрушка и заносится во временный список. Далее, случайным образом, выбирается следующая игрушка,   
и заносится во временный список. Таким образом формируется выборка из 100 игрушек. Количество попаданий каждой игрушки в этот временный список вычисляется  
и умножается на соответствующий весовой коэффициент. Таким образом получаются баллы, которые "набрала" каждая игрушка. Баллы сравниваются и выбирается 
игрушка, набравшая наибольшее количество баллов. При равенстве баллов у всех игрушек, выбирается первая из списка.

### Выдача игрушек.
Игрушка, которая была выбрана в процессе розыгрыша, "выдается" покупателю путем помещения записи о выдаче игрушки в файл. Игрушка после выдачи покупателю удаляется
из списка всех игрушек магазина.

***Каждая игрушка имеет уникальный идентификационный номер (ID).***

### Весовой коэффициент. 
Весовой коэффициент используется для целей определения игрушки, "победившей" в процессе розыгрыша.
Для его изменения, необходимо выбрать из списка игрушек нужную (запомнить ID игрушки). Список игрушек можно вывести на экран, выбрав соответствующий пункт меню.
Затем, выбрать пункт меню "Изменить весовой коэффициент Игрушки" и ввести ID той игрушки, весовой коэффициент которой требуется изменить. Далее вводится новый
весовой коэффициет.

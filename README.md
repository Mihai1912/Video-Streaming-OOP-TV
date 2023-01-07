# POO_proiect_etapa_2


Popescu Mihai-Costel 324CD
## Explicitarea claselor nou adaugate

### ---  Notification  ---
*  In clasa Notification se gasesc 2 campuri de tip String care reprezinta numele filmului care este
adaugat respectiv sters, iar celalalt camp este campul in care se stocheaza mesajulnotificarii adica 
ADD restectiv DELETE

### ---  Back  ---
*  In clasa Back se implementeaza pattern-ul singleton iar apoi se implementeaza metoda de back, in 
aceasta metoda se verifica la inceput ca daca stiva de pagini este nula sau daca este goala daca
aceste conditii sunt adevarate se printeaza eroarea standard in fisierul de output, daca nu se trece
la pasul urmator, unde se verifica daca sitva are mai mult de 2 elemente pentru a se putea realiza
actinea de back. Se retine extrage pagina curenta din stiva iar pagina anterioara se retine cu ajutorul
unei variabile, daca pagina curenta este homePageAuthentify se adauga pagina curenta in stiva si se
printeaza eroarea standard. Tot eroarea standard este fisata si daca pagina anterioara este login sau
register deoarece nu este permisa navigarea pe aceste pagini. Daca pagina anterioara este movies se verifica 
daca actiune se realizeaza cu succes pentru a printa outputul normal daca nu se printeaza eroarea standard
, iar la sfarsit se seteaza pagina curenta ca fiind pagina precedenta practic se realizeaza actiunea de back.


### ---  Database  ---
*  In clasa Database se implementeaza pattern-ul singleton iar apoi se implementeaza metoda database,
in aceasta sunt prezente 2 cazuri unul in care se adauga film in baza de date a filmelor si alta cand se scoate
un film din baza de date a filmelor. In cazul in care trebuie adaugat un film se verifica daca filmul
exista deja in baza de date a filmelor, daca da printeaza eroarea standard, daca nu se adauga filmul
in baza de dare a filmelor, dupa care se ia fiecare user se verifica daca filmul este disponibil in 
tara user-ului, iar apoi se verifica daca userul este abonat la un gen de filme care se regaseste si
in lista de genuri a filmului daugat, daca acesta este abonat se trimire o notificare catre acesta
in care este instiintat ca filmul a fost adaugat. Un alt caz prezent este cel in care trebuie sters
un film din baza de date a filmelor, in acest caz se verifica daca filmul pe care dorim sa il stergem
este in baza de date a filmelor, daca acesta nu este se printeaza eroarea standard, daca acesta este
se sterge din baza de date a filmelor, iar apoi se ia fiecare user in pare si verificam daca acesta
are filmul sters anterior din baza de date in baza sa de date a filmelor cumparate/vazute/apreciate...
daca acesta are filmul respectiv il stergem din baza de date, iar la sfarsit ii trimitem o notificare
in care este instiintat ca filmul a fost sters, si isi primeste creditele inapoi daca acesta este user
standard, iar daca acesta este user premium primeste un film gratis.

### ---  Recommendation  ---
*  In clasa recommendation se creaza un arraylist in care sunt puse genurile de filme apreciate de
user-ul curent, acestea sunt puse in ordine crescatoare, arraylistul fiind inversat ulterior pentru a
a fi in ordine descrescatoare. Se creaza lista de filme nevazute de utilizator dupa care se sorteaza
in ordine descrescatoare in functie de numarul de like-uri. Iar ultima etapa este aceea in care 
i se recomanda userului cel mai apreciat filme nevazut care are in lista de genuri cel mai apreciat
gen de catre utilizator. Daca acest film nu a fost gasit nu i se recomanda niciun film.

## Explicitarea modificarilor facute in clasele din etapa 1

### ---  Functia subscribe din clasa SeeDetailsPage  ---
* In clasa SeeDetailsPage s-a adaugat o noua finctionalitatea cea de subscribe, implementarea acestei
functionalitati este ca se ia fiecare gen al filmului pentru care s-a apelat seedetails si se verifica
daca acesta este genul de film pentru care s-a dat subscribe daca da nu se primteaza nimic, iar daca 
genul de film pentru care s-a dat subscribe nu s-a gasit se printeaza eroarea standard.

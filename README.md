# Des Java Beans au DDD

_Sortir de sa zone de confort_

Le DDD, on en parle beaucoup.
Ceux qui le pratiquent ne tarissent pas d'éloges.
Mais qu'est-ce ? Et surtout, à quoi cela sert-il ?

Plutôt que d'essayer de résumer le DDD en quelques lignes (mission impossible), l’objet de cet article vise plutôt à montrer l'intérêt qu'ont les patterns tactiques du DDD, et surtout de les comparer avec une programmation classique à la JavaBean.

A l’aide d’un exemple très simple et en présentant quelques patterns tactiques (Value Object, Entity, Aggregate), cet article propose une petite découverte d'une partie du DDD.

Nous espérons ainsi donner envie d'aller voir plus loin et de découvrir la grande richesse du DDD : l'aspect stratégique.


## L’exemple : Le recrutement des futurs consultants

Commençons par l’exemple qui nous est donné par notre division RH : **Nous devons disposer d’une plateforme numérique afin de pouvoir recruter efficacement et rapidement nos futurs consultants** 

Nous allons considérer que nous avons déjà :

* un dépôt de C.V. accessible via une API.
* un service permettant de réserver des salles, lui assi accessible via une API.

Reste à construire le système qui permet de suivre le processus de recrutement des futurs consultants : 

1. planifier l'entretien avec le candidat, celui-ci doit être réalisé par un consultant recruteur comprenant le profil du poste à pourvoir.
2. permettre au consultant qui a réalisé l'entretien de saisir son avis
3. en cas d'avis positif, envoyer une proposition au candidat
4. attendre sa réponse (délai de 10j) et, en cas de réponse positive de la part du candidat, passer le C.V. au service d'embauche.

Nous considérons donc les concepts suivants : 

## Les concepts clés

### Le Candidat (Canditate)

### Le Profile

### La Competence 
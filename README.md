# Des Java Beans au DDD 

_Vers une approche moderne de la programmation orientée objet_

L’objet de cet article est d’illustrer la force des concepts « modernes » du DDD et de les opposer aux principes « dépassés » des JavaBeans.
Mon objectif est de présenter trois concepts importants du DDD (Value Object, Service, Entity) et d’expliquer la programmation moderne qu’ils permettent. A l’aide d’un exemple très simple, je vais leur opposer une conception JavaBeans.
En lisant cet article, vous allez pouvoir comprendre une partie des concepts du DDD (3 concepts) et mieux situer l’innovation de ces concepts par rapport à une programmation classique. Vous pourrez ainsi opposer ces concepts du DDD aux concepts classiques des JavaBeans. 

## L’exemple : Les Todo

Commençons par l’exemple : des Todo. L’objet est de pouvoir ajouter et supprimer des Todo dans une list. Puis de pouvoir dire qu’un todo est fait (qu’il n’est plus « à faire »). Enfin, on veut pouvoir afficher les todo à faire, et ceux déjà effectués.

A titre de test, je propose le petit scenario suivant :

1. Créer une liste vide de Todo
2. Créer un Todo (« code damn it ») et l’ajouter dans la liste
3. Créer un deuxième Todo (« code twice damn it ») et l’ajouter dans la liste
4. Vérifier qu’il y a bien 2 Todo
5. Faire en sorte que le premier Todo soit fait
6. Afficher les Todo à faire et vérifier qu’il n’y en a qu’un
7. Afficher les Todo terminés et vérifier qu’il n’y en a qu’un

## Conception Java Bean

Un JavaBean a des propriétés qui sont accessibles via des getter (en lecture) ou des setter (en écriture). 
Pour concevoir l’application des Todo, j’ai donc besoin de 2 JavaBean : le Todo et la liste des todo (TodoList).
Un Todo a une description et il est soit terminé, soit à faire (boolean done).

``` java
package fr.jb;

public class Todo {
    private String description;
    private boolean done;

    public Todo() {
        this.done = false;
    }

    public Todo(String description) {
        this.description = description;
        this.done = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getDone() {
        return this.done;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
```

Le code **Todo.java** est relativement simple. Deux propriétés avec des getter et des setter. A la limite, on peut considérer que la description ne va pas changer et donc supprimer setDescription.
Notons enfin qu’à sa création, un Todo est « à faire » (this.isDone = false).

Pour concevoir **TodoList.java** j’ai besoin d’une liste de Todo (List<Todo>), du getter sur cette liste qui va retourner une copie de la liste, des méthodes pour ajouter et supprimer les todo, et enfin de deux méthodes pour obtenir les Todo à faire ou ceux terminés.

``` java
package fr.jb;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private List<Todo> todoList;

    public TodoList() {
        todoList = new ArrayList<Todo>();
    }

    public List<Todo> getTodoList() {
        return new ArrayList<Todo>(this.todoList);
    }

    public void addTodo(Todo t) {
        if (todoList.contains(t)) throw new IllegalArgumentException("Todo already in the List");
        todoList.add(t);
    }

    public void removeTodo(Todo t) {
        todoList.remove(t);
    }

    public List<Todo> getUndoneTodo() {
        return getTodo(false);
    }

    public List<Todo> getDoneTodo() {
        return getTodo(true);
    }

    private List<Todo> getTodo(boolean isDone) {
        List<Todo> List = new ArrayList<Todo>();
        for (Todo todo : todoList) {
            if (todo.getDone() == isDone) {
                List.add(todo);
            }
        }
        return List;
    }
}
```

Notons que pour rendre le code plus lisible j’ai factorisé le code des méthodes getUnDoneTodo() et getDoneTodo dans la méthode getTodo.

Avec cette conception, mon test devient :

``` java
package fr.jb;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class TestTodo {

    @Test
    public void test() {
        TodoList todoList = new TodoList();
        Todo todo1 = new Todo("code damn it");
        todoList.addTodo(todo1);
        Todo todo2 = new Todo("code twice damn it");
        todoList.addTodo(todo2);
        assertEquals(2, todoList.getTodoList().size());

        todo1.setDone(true);
        
        List<Todo> undone = todoList.getUndoneTodo();
        assertEquals(1, undone.size());
        
        List<Todo> done = todoList.getDoneTodo();
        assertEquals(1, done.size());
    }
}
```

### Quel est le problème me diriez-vous ?

**C’est : todo1.setDone(true)**

En effet le problème principal est que le changement de l’état des Todo ne soit pas réalisé par TodoList mais par Todo.

Autrement dit, TodoList n’est responsable que de l’ensemble des todo (add et remove) et pas de l’état des todo.

Si on veut changer cela, il faut alors encapsuler les Todo dans TodoList, et là, c’est très compliqué à coder. On pourrait faire en sorte que Todo soit une classe interne de TodoList mais ce n’est vraiment pas très lisible, et il faudrait gérer les types de retours. On pourrait demander à TodoList de faire une copie des Todo lors de l’ajout, et là encore cela va rendre le code très compliqué.

### Y-a-t-il un autre problème ?

C’est moins flagrant, mais les deux méthodes getUndoneTodo et getDoneTodo sont en fait des requêtes (des recherches) réalisées sur l’ensemble des Todo. On peut se poser la question de l’intérêt de les mettre dans la classe TodoList car finalement, elles n’ont besoin que d’une seule chose, c’est d’un ensemble de todo (List<Todo>).

## Conception DDD

La conception DDD est très moderne. Mon objectif est ici de n’en montrer qu’une sous partie. J’ai choisi d’illustrer trois concepts : **Value Object**, **Service** et **Entity**.

Le postulat est le suivant, certes tout est objet, mais tous les objets ne sont pas les mêmes. Il est important de distinguer certains types d'objet : **Value Object**, **Service** et **Entity**.

Un **Value Object** est un objet qui représente une valeur et même une constante. L’exemple le plus utilisé est celui du point GPS avec la latitude et la longitude. Chaque point GPS est défini par sa latitude et sa longitude. Du coup, aucun intérêt à changer les valeurs (pas de setter).

Un **Service** est un objet qui offre une ou plusieurs fonctions (stateless si possible). De fait on peut construire un Service, l’utiliser et le supprimer. Un service n’a pas réellement d’état, c’est juste un ensemble de fonctions.

Un **Entity** est un objet qui est identifiable et qui a un état propre qui va changer. L’Entity est l’objet « noble » de la programmation orienté objet. Par contre, plutôt que des setter, il offre des méthodes métiers qui vont permettre d’exécuter l’application (on choisira des noms explicites pour ces méthodes).

Une fois ces trois concepts connus, le DDD préconise l’utilisation de Value Object et de Service plutôt que d’Entity. En d’autres mots, il faut essayer de manipuler le plus possible de Value Object et de Service.

En reprenant notre application, et en suivant à l’extrême le principe du DDD, on peut faire en sorte que le Todo soit une value object. On va donc considérer qu’il n’est pas possible de modifier un Todo après sa construction !

``` java
package fr.ddd;

import java.util.Objects;

public class Todo {
    private String description;
    private boolean isDone;

    public Todo(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Todo(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public boolean equals(Object other) {
        if (! (other instanceof Todo)) return false;
        Todo otherTodo = (Todo) other;
        boolean sameDescription =  otherTodo.getDescription().compareTo(description) == 0;
        boolean done = otherTodo.isDone() == isDone;
        return sameDescription && done;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        return description;
    }
}
```

Le code est très simple. Deux propriétés, initialisées à la construction, et des getters.
J’ai recodé les méthodes equals, hashCode et toString car deux Todo sont identiques si et si seulement si leur description et leur réalisation sont identiques. Notons qu’il ne sert à rien de recoder ces méthodes dans le cas des JavaBeans car l’égalité des JavaBeans est portée par les identifiants Java.

Ensuite, je peux sortir les deux requêtes de la classe TodoList. En suivant les principes du DDD, je construis un Service qui contient les deux méthodes de recherche.

``` java
package fr.ddd;

import java.util.ArrayList;
import java.util.List;

public class SearchTodo {
    public List<Todo> findUndoneTodo(List<Todo> todoList) {
        return findTodo(false, todoList);
    }

    public List<Todo> findDoneTodo(List<Todo> todoList) {
        return findTodo(true, todoList);
    }

    private List<Todo> findTodo(boolean isDone, List<Todo> todoList) {
        List<Todo> set = new ArrayList<Todo>();
        for (Todo todo : todoList) {
            if (todo.isDone() == isDone) {
                set.add(todo);
            }
        }
        return set;
    }
}
```

Là encore le code est relativement simple. J’ai une classe avec deux méthodes stateless. On pourrait même les mettre en static mais je préfère les laisser dans les objets.

Pour finir, TodoList est une Entity. Il contient l’ensemble des Todo et surtout les méthodes permettant de dire qu’un Todo est fait (regardez le code, je supprime le Todo « à faire » et le remplace par un nouveau Todo « fait »).

``` java
package fr.ddd;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private List<Todo> todoList;

    public TodoList() {
        todoList = new ArrayList<Todo>();
    }

    public List<Todo> getTodoList() {
        return new ArrayList<Todo>(this.todoList);
    }

    public void addTodo(Todo t) {
        if (todoList.contains(t)) throw new IllegalArgumentException("Todo already in the list");
        todoList.add(t);
    }

    public void removeTodo(Todo t) {
        todoList.remove(t);
    }

    public void didIt(Todo todo) {
        if (!todoList.contains(todo)) throw new IllegalArgumentException("Todo not in the list");
        int index = todoList.lastIndexOf(todo);
        todoList.remove(index);
        todoList.add(index, new Todo(todo.getDescription(), true));
    }
}
```

Avec ce code, mon exemple devient :

``` java
package fr.ddd;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class TestTodo {

    @Test
    public void test() {
        TodoList todoList = new TodoList();
        Todo todo1 = new Todo("code damn it");
        todoList.addTodo(todo1);
        Todo todo2 = new Todo("code twice damn it");
        todoList.addTodo(todo2);
        assertEquals(2, todoList.getTodoList().size());

        todoList.didIt(todo1);
        
        SearchTodo search = new SearchTodo();
        List<Todo> undone = search.findUndoneTodo(todoList.getTodoList());
        assertEquals(1, undone.size());

        List<Todo> done = search.findDoneTodo(todoList.getTodoList());
        assertEquals(1, done.size());
    }
}
```

Dans le code le changement principal avec la conception JavaBean est porté par la ligne : todoList.didIt(todo1)

Là c’est clair, c’est TodoList qui est responsable du changement d’état de ses Todo.
On voit aussi que l’utilisation des Service se fait en mode Stateless ce qui permettra, si on a plus de ressources, de mieux paralléliser l’exécution.

## Ce qu’il faut retenir

Avec cet exemple, mon intention est de vous montrer qu’il faut sortir de la conception « à la JavaBean ». Les concepts modernes exprimés entre autres par le DDD vous permettent d’explorer de nouvelles conceptions.

Mon exemple de Todo avec le DDD est, de mon point de vue, beaucoup plus intéressant que celui avec les JavaBeans. Si vous n'êtes pas convaincu et si vous trouvez que j'ai forcé le trait (c'est pas complètement faux), j'espère au moins que cet exemple vous aura fait réfléchir et donner envie d'aller plus loin dans le DDD.

Néanmoins, pour vous convaincre davantage, essayez de mettre en place une méthode de notification permettant de savoir que le Todo d’une TodoList donné vient de changer. Avec le DDD, il faut simplement ajouter un système de notification sur la TodoList, en envoyant la notification dans le code de didIt. Avec les JavaBean le Todo a oublié dans quelle TodoList il est … C’est le fameux problème de l’amnésie du code.

Pour résumer, cet exemple a pour objectif de vous montrer un petit bout du DDD. Les concepts qu'il propose sont vraiment redoutables et vous permettront d'améliorer grandement vos conceptions.

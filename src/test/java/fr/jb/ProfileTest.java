package fr.jb;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ProfileTest {

    @Test
    public void test() {
        Skill sk1 = new Skill("Java");
        Skill sk2 = new Skill("C#");
        Skill sk3 = new Skill("Java");
        Expertise exp1 = new Expertise(sk1, Level.SECOND);
        Expertise exp2 = new Expertise(sk2, Level.THIRD);
        Expertise exp3 = new Expertise(sk3, Level.THIRD);
        Set<Expertise> expertiseSet = new HashSet()<Expertise>();
        expertiseSet.add(exp1);
        expertiseSet.add(exp2);
        expertiseSet.add(exp3);
        Profile profile = new Profile(expertiseSet);
        assertEquals(1, done.size());
    }
}
package java8.ex02;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Exercice 02 - Map
 */
public class Lambda_02_Test <T>{

    // tag::PersonToAccountMapper[]
    interface PersonToAccountMapper<E,S> {
        S map(E p);
    }

    // end::PersonToAccountMapper[]

    // tag::map[]
    private<E,S> List<S> map(List<E> personList, PersonToAccountMapper<E,S> mapper) {
        // TODO implémenter la méthode pour transformer une liste de personnes en liste de comptes
    	List<S> result = new ArrayList<S>();
    	for(E p:personList){
    		result.add(mapper.map(p));
    	}
        return result;
    }
    // end::map[]


    // tag::test_map_person_to_account[]
    @Test
    public void test_map_person_to_account() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        // TODO transformer la liste de personnes en liste de comptes
        // TODO tous les objets comptes ont un solde à 100 par défaut
        List<Account> result = ((List<Account>) map(personList,m ->{
        	Account a = new Account();
        	int balance = 100;
			a.setBalance(balance);
        	a.setOwner(m);
        	return a;
        	
        }));

        assert result.size() == personList.size();
        for (Account account : result) {
            assert account.getBalance().equals(100);
            assert account.getOwner() != null;
        }
    }
    // end::test_map_person_to_account[]

    // tag::test_map_person_to_firstname[]
    @Test
    public void test_map_person_to_firstname() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        // TODO transformer la liste de personnes en liste de prénoms
        List<String> result =((List<String>) map(personList,m ->{
        	return m.getFirstname();
        }));

        assert result.size() == personList.size();
        for (String firstname : result) {
            assert firstname.startsWith("first");
        }
    }
    // end::test_map_person_to_firstname[]
}
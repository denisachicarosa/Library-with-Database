package Services;

import Persons.Client;
import com.Model.Book;
import com.Model.Library;
import com.Model.Movie;

public class FindStuff {
    static Library l;
    public FindStuff(Library l) {
        this.l = l;
    }

    static public Client findClientByName(String first, String last) {
        for(Client c : l.getClients()) {
            if (c.getFirstName().equals(first) && c.getLastName().equals(last)) return c;
        }
        Client c = new Client();
        c.setFirstName("Nu exista");
        return c;
    }
    static public Book findBookByIndex(Integer  id)
        {
        for (Book b :l.getBooks()) {
            if(b.getID() == id)
                return b;
        }

        Book b = new Book();
        b.setTitle("Nu exista");
        return b;
    }
    static public Movie findMovieByIndex(Integer id) {
        for (Movie m :l.getMovies()) {
            if(m.getID() == id)
                return m;
        }

        Movie b = new Movie();
        b.setTitle("Nu exista");
        return b;
    }

}


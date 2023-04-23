package org.example.dao;

import org.example.model.Jedi;

import java.util.List;

public interface JediDAO {
    List<Jedi> findAll();
    Jedi save(Jedi jedi);
    void delete(Jedi jedi);
    void delete(int JediId);

    Jedi findById(int jediId);
}

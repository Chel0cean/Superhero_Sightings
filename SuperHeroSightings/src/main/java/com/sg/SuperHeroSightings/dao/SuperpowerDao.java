package com.sg.SuperHeroSightings.dao;

import com.sg.SuperHeroSightings.dto.Superpower;
import java.util.List;

/**
 *
 * @author Chelsea, Karma, Mohammed, Patrick
 */
public interface SuperpowerDao {

    Superpower getSuperpowerById(int id);

    List<Superpower> getAllSuperpowers();

    Superpower addSuperpower(Superpower superPower);

    void updateSuperpower(Superpower superPower);

    void deleteSuperpowerById(int id);
}

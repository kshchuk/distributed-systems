package org.example.service;

import org.example.dao.AirlineDao;
import org.example.model.Airline;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AirlineServiceImpl implements AirlineService {
    private AirlineDao dao;

    public AirlineServiceImpl(AirlineDao dao) {
        this.dao = dao;
    }

    @Override
    public void create(Airline airline) {
        try {
            dao.create(airline);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Airline> findAll() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Airline> findAllByCountry(String country) {
        try {
            return dao.findAll().stream()
                    .filter(airline -> airline.getCountry().equals(country))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delete(UUID id) {
        try {
            dao.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void update(Airline airline) {
        try {
            dao.update(airline);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean containsId(UUID id) {
        try {
            return dao.read(id) != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void changeSource(AirlineDao dao) {
        this.dao = dao;
    }

    @Override
    public Airline get(UUID id) {
        try {
            return dao.read(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
package org.example.service;

import org.example.dao.AirlineDao;
import org.example.dao.FlightDao;
import org.example.model.Flight;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class FlightServiceImpl implements FlightService {
    private FlightDao dao;
    private AirlineDao airlineDao;

    public FlightServiceImpl(FlightDao dao, AirlineDao airlineDao) {
        this.dao = dao;
        this.airlineDao = airlineDao;
    }

    @Override
    public void create(Flight flight) {
        try {
            var airline = airlineDao.read(flight.getAirline_id());
            if (airline == null) {
                throw new Exception("Airline with id " + flight.getAirline_id() + " not found");
            }
            airline.getFlights().add(flight);
            airlineDao.update(airline);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Flight> findAll() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Flight> findAllByAirline(UUID airlineId) {
        try {
            var airline = airlineDao.read(airlineId);
            if (airline == null) {
                throw new Exception("Airline with id " + airlineId + " not found");
            }
            return airline.getFlights();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Flight> findAllByOrigin(String origin) {
        try {
            return dao.findAll().stream()
                    .filter(flight -> flight.getOrigin().equals(origin))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Flight> findAllByDestination(String destination) {
        try {
            return dao.findAll().stream()
                    .filter(flight -> flight.getDestination().equals(destination))
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
    public void update(Flight flight) {
        try {
            var airline = airlineDao.read(flight.getAirline_id());
            if (airline == null) {
                throw new Exception("Airline with id " + flight.getAirline_id() + " not found");
            }
            var flights = airline.getFlights();
            for (int i = 0; i < flights.size(); i++) {
                if (flights.get(i).getId().equals(flight.getId())) {
                    flights.set(i, flight);
                    break;
                }
            }
            airlineDao.update(airline);
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
    public void changeSource(FlightDao dao) {
        this.dao = dao;
    }

    @Override
    public Flight get(UUID id) {
        try {
            return dao.read(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

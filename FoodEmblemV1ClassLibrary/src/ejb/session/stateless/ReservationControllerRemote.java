/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Reservation;

public interface ReservationControllerRemote {

    public Reservation createReservation(Reservation newReservation , String customerId , Long seatId);
}

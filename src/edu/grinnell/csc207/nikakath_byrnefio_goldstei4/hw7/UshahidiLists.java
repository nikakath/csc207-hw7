package edu.grinnell.csc207.nikakath_byrnefio_goldstei4.hw7;

import java.io.PrintWriter;
import edu.grinnell.glimmer.ushahidi.*;

/**
 * Methods involving UshahidiIncidents and DoublyLinkedLists
 */
public class UshahidiLists {

	/**
	 * Takes an UshahidiIncidentList ls and returns a doubly-linked list
	 * of UshahidiIncidents from ls that fall within a certain distance
	 * of the average latitude and longitudes of the UshahidiIncidents
	 * within ls.
	 * 
	 * @post list contains a sublist of ls
	 * 
	 * @throws Exception
	 */
	
	public DoublyLinkedList<UshahidiIncident> extract(UshahidiIncidentList ls) 
			throws Exception {
		
		DoublyLinkedList<UshahidiIncident> list = new 
				DoublyLinkedList<UshahidiIncident>();
		DoublyLinkedListCursor<UshahidiIncident> curs = new 
				DoublyLinkedListCursor(list.front);
		UshahidiIncident current;
		UshahidiLocation local;
		double lat = 0; /* average latitude of ls */
		double lon = 0; /* average longitude of ls*/
		double la = 0; /* current latitude */
		double lo = 0; /* current longitude */
		double distance = 100.0;
		int i = 0;
		
		// Move the UshahidiIncidents into the doubly-linked list and calculate
		// the average latitude and longitude
		while (ls.hasMoreIncidents()) {
			current = ls.nextIncident();
			local = current.getLocation();
			lat = lat + local.getLatitude();
			lon = lon + local.getLongitude();
			list.append(current);
			i += 1;
		}
		
		// Calculate the final averages
		lat = lat/i;
		lon = lon/i;
		
		// Set the boundaries for the distance to select UshahidiIncidents from
		double maxla = lat + distance;
		double minla = lat - distance;
		double maxlo = lon + distance;
		double minlo = lon - distance;
		
		// Delete UshahidiIncidents from list that are outside the distance 
		// boundaries
		while (list.hasNext(curs)) {
			current = list.get(curs);
			local = current.getLocation();
			la = local.getLatitude();
			lo = local.getLongitude();
			if ((la > maxla || la < minla) || (lo > maxlo || lo < minlo)) {
				list.delete(curs);
			}
		}
		return list;
	} // extract(UshahidiIncidentList)

	/**
	 * Takes an UshahidiIncidentList and prints out useful information about
	 * the UshahidiIncidents in the list.
	 * 
	 * @post Prints correct information related to the UshahidiIncidents within
	 * 	 	  the original list.
	 *
	 */
	public void summary(PrintWriter pen, UshahidiIncidentList ls) 
			throws Exception {
		
		DoublyLinkedList<UshahidiIncident> list = new 
				DoublyLinkedList<UshahidiIncident>();
		DoublyLinkedListCursor<UshahidiIncident> curs = new 
				DoublyLinkedListCursor(list.front);
		UshahidiIncident current;
		UshahidiLocation local;
		double lat = 0; /* average latitude of ls */
		double lon = 0; /* average longitude of ls */
		int i = 0;
		
		// Move the UshahidiIncidents into the doubly-linked list and calculate
		// the average latitude and longitude
		while (ls.hasMoreIncidents()) {
			current = ls.nextIncident();
			local = current.getLocation();
			lat = lat + local.getLatitude();
			lon = lon + local.getLongitude();
			list.append(current);
			i += 1;
		}

		// Print the useful information
		pen.println("Number of Incidents: " + i);
		
		pen.println("Incident Names:");
		while(list.hasNext(curs)) {
			pen.println(list.get(curs).getTitle());
		}
		
		curs.pos = list.front; /* reset the cursor to the front of the list */
		
		pen.println("Incident Locations:");
		while(list.hasNext(curs)) {
			pen.println(list.get(curs).getLocation().getName());
		}
		
		pen.println("Average latitude of incidents: " + lat/i);
		pen.println("Average longitude of incidents: " + lon/i);
	} // summary(PrintWriter, UshahidiIncidentList)
} // class UshahidiLists

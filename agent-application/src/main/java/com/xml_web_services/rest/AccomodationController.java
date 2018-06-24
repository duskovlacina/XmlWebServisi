package com.xml_web_services.rest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xml_web_services.domain.Accomodation;
import com.xml_web_services.domain.Agent;
import com.xml_web_services.domain.Images;
import com.xml_web_services.domain.Reservation;
import com.xml_web_services.help_classes.ReservationHelper;
import com.xml_web_services.services.AccomodationService;
import com.xml_web_services.services.ImagesService;

@RestController
@RequestMapping("/accomodation")
public class AccomodationController {

	@Autowired
	private AccomodationService accomodationService;
	
	@Autowired
	private ImagesService imagesService;
	
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Accomodation>> getAccomodations() {
		return new ResponseEntity<>(this.accomodationService.getAccomodations(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Accomodation> create(@RequestBody Accomodation accomodation, HttpServletRequest request) {
		accomodation.setAgent((Agent)request.getSession().getAttribute("agent"));
		Accomodation a = this.accomodationService.save(accomodation);
		if (a != null) return new ResponseEntity<>(a, HttpStatus.CREATED);
		return new ResponseEntity<>(a, HttpStatus.BAD_REQUEST);
	}
	
	
	@RequestMapping(value="/upload-multiple-files", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Accomodation> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
    		@RequestParam("accomodation") String name) throws IOException {
		
		
		Accomodation accom = this.accomodationService.findByName(name);
		if (accom == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		accom.getImages().addAll(saveImages(files));
		
		this.accomodationService.sendAccomodationToServer(this.accomodationService.save(accom));
		
		return new ResponseEntity<>(null, HttpStatus.OK);
    }
	
	private List<Images> saveImages(MultipartFile[] files) {
		List<Images> images = new ArrayList<>();
		for(MultipartFile mf : files) {
			try {
				FileOutputStream out = new FileOutputStream("" + mf.getOriginalFilename());
				out.write(mf.getInputStream().read());
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Images image = new Images();
			
			image.setUrl("" + mf.getOriginalFilename());
			images.add(this.imagesService.save(image));
		}
		
		return images;
	}
	
	
	@RequestMapping(value="/reserve", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Reservation> reserveAccomodation(@RequestBody ReservationHelper reservationHelper,
								HttpServletRequest request) {
		System.out.println(reservationHelper.getAccomodationId());
		
		Reservation reservation = this.accomodationService.tryToReserve(reservationHelper,
				(Agent)request.getSession().getAttribute("agent"));
		
		if (reservation != null) {
			return new ResponseEntity<>(reservation, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
}

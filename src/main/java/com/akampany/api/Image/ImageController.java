package com.akampany.api.Image;

import java.util.Optional;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController

@RequestMapping("/image")
public class ImageController {

	private final ImageRepository imageRepository;

	public ImageController(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}

	@GetMapping("/getImage/{id}")
	public ResponseEntity<ByteArrayResource> getImage(@PathVariable Long id) {
		Optional<Image> imageOptional = imageRepository.findById(id);

		if (imageOptional.isPresent()) {
			System.out.println("hello there");
			Image imageEntity = imageOptional.get();
			ByteArrayResource resource = new ByteArrayResource(imageEntity.getImageData()) ;
			
			System.out.println(resource.contentLength());
			
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).contentLength(resource.contentLength())
					.body(resource);
		} else {
			// Image not found
			return ResponseEntity.notFound().build();
		}
	}

	

	@PostMapping("/{userId}")
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable Long userId) {

		Image imageEntity = new Image();
		imageEntity.setId(userId);
		imageEntity.setImageName(file.getOriginalFilename());
		imageEntity.setDescription(file.getContentType());
		try {
			imageEntity.setImageData(file.getBytes());
		} catch (java.io.IOException e) {

			e.printStackTrace();
		}
		imageRepository.save(imageEntity);

		return ResponseEntity.ok().body("{\"message\": \"Image uploaded successfully\"}");

	}

	@DeleteMapping("/delete/{id}")
	public void deleteImage(@PathVariable Long id) {
		
		imageRepository.deleteById(id);

	}
}

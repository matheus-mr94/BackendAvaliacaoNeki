package br.com.neki.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.neki.exceptions.EmailOrPasswordNotValidException;
import br.com.neki.exceptions.HabilidadeException;
import br.com.neki.exceptions.HabilidadeNotFoundException;
import br.com.neki.exceptions.HabilidadeUsuarioException;
import br.com.neki.exceptions.HabilidadeUsuarioNotFoundException;
import br.com.neki.exceptions.UsuarioException;
import br.com.neki.exceptions.UsuarioNotFoundException;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

	private HttpHeaders header(Exception ex) {
		HttpHeaders header = new HttpHeaders();
		header.add("LIBRARY", "NEKI IT");
		header.add("x-error-msg", ex.getMessage());

		return header;
	}

	@ExceptionHandler(UsuarioException.class)
	public ResponseEntity<String> handlerUsuarioException(UsuarioException ex) {
		return ResponseEntity.badRequest().headers(this.header(ex)).build();
	}

	@ExceptionHandler(UsuarioNotFoundException.class)
	public ResponseEntity<String> handlerUsuarioNotFoundException(UsuarioNotFoundException ex) {
		return ResponseEntity.notFound().headers(this.header(ex)).build();
	}

	@ExceptionHandler(HabilidadeException.class)
	public ResponseEntity<String> handlerHabilidadeException(HabilidadeException ex) {
		return ResponseEntity.badRequest().headers(this.header(ex)).build();
	}

	@ExceptionHandler(HabilidadeNotFoundException.class)
	public ResponseEntity<String> handlerHabilidadeNotFoundException(HabilidadeNotFoundException ex) {
		return ResponseEntity.notFound().headers(this.header(ex)).build();
	}

	@ExceptionHandler(HabilidadeUsuarioException.class)
	public ResponseEntity<String> handlerHabilidadeUsuarioException(HabilidadeUsuarioException ex) {
		return ResponseEntity.badRequest().headers(this.header(ex)).build();
	}

	@ExceptionHandler(HabilidadeUsuarioNotFoundException.class)
	public ResponseEntity<String> handlerHabilidadeUsuarioNotFoundException(HabilidadeUsuarioNotFoundException ex) {
		return ResponseEntity.notFound().headers(this.header(ex)).build();
	}

	@ExceptionHandler(EmailOrPasswordNotValidException.class)
	public ResponseEntity<String> handlerEmailOrPasswordNotValidException(EmailOrPasswordNotValidException ex) {
		return ResponseEntity.badRequest().headers(this.header(ex)).build();
	}

}

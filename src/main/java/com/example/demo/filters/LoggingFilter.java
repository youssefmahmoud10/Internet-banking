package com.example.demo.filters;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author YoussefMahmoud
 * @created Apr 23, 2023-4:02:40 PM
 */

@Component
public class LoggingFilter extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper(response);

		long startTime = System.currentTimeMillis();
		filterChain.doFilter(contentCachingRequestWrapper, contentCachingResponseWrapper);
		long timeTaken = System.currentTimeMillis() - startTime;

		String requestBody = getStringValue(contentCachingRequestWrapper.getContentAsByteArray(),
				request.getCharacterEncoding());
		String responseBody = getStringValue(contentCachingResponseWrapper.getContentAsByteArray(),
				response.getCharacterEncoding());

		log.info(
				"FINISHED PROCESSING : METHOD={}; REQUESTURI={}; REQUEST PAYLOAD={}; RESPONSE CODE={}; RESPONSE={}; TIM TAKEN={}",
				request.getMethod(), request.getRequestURI(), requestBody, response.getStatus(), responseBody,
				timeTaken);
		contentCachingResponseWrapper.copyBodyToResponse();

	}

	private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
		try {
			return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

}
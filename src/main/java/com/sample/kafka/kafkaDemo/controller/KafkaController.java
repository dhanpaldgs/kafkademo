package com.sample.kafka.kafkaDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.kafka.kafkaDemo.service.KafKaProducerService;
import com.sample.kafka.kafkaDemo.service.KafkaSender;

@RestController
@RequestMapping(value = "/kafka/")
public class KafkaController {

	@Autowired
	KafkaSender kafkaSender;
	
	@Autowired
	KafKaProducerService producerService;

	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestParam("message") String message)
	{
		this.producerService.sendMessage(message);
	}

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("message") String message) {
		kafkaSender.send(message);

		return "Message sent by kafka producer:"+message;
	}

}
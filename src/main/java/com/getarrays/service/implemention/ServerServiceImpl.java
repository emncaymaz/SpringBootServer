package com.getarrays.service.implemention;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.getarrays.enumeration.Status;
import com.getarrays.model.Server;
import com.getarrays.repository.ServerRepository;
import com.getarrays.service.ServerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService {
	
	private final ServerRepository serverRepository;
	

	@Override
	public Server create(Server server) {
		log.info("Saving new server: {} ", server.getName());
		server.setImageUrl(setServerImageUrl());
		return serverRepository.save(server);
	}
	

	@Override
	public Server ping(String ipAddress) throws IOException {
		log.info("Saving new server: {} ", ipAddress);
		Server server = serverRepository.findByIpAddress(ipAddress);
		InetAddress address = InetAddress.getByName(ipAddress);
		server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
		return server;
		
	}

	@Override
	public Collection<Server> list(int limit) {
		log.info("Fetching all server");
		return serverRepository.findAll()
								.stream()
								.filter(s -> s.getId() <limit)
								.collect(Collectors.toList());
	}

	@Override
	public Server get(Long id) {
		log.info("Fetching server by id: {} ", id);
		return serverRepository.findById(id).get();
	}

	@Override
	public Server update(Server server) {
		log.info("Upadte server: {} ", server.getName());
		return serverRepository.save(server);
	}

	@Override
	public Boolean delete(Long id) {
		log.info("Delete server: {} ", id);
		serverRepository.deleteById(id);
		return true;
	}

	private String setServerImageUrl() {
		String [] imageNames = {"server1.png","server1.png","server1.png","server1.png"};
		return ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/server/image/" + imageNames[new Random()
				                                    .nextInt(4)])
													.toUriString();
	}

}

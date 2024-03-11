package by.dzmitry.yarashevich.services;

import by.dzmitry.yarashevich.dto.UserCreateEditDto;
import by.dzmitry.yarashevich.dto.UserReadDto;
import by.dzmitry.yarashevich.dto.mapper.UserEditMapper;
import by.dzmitry.yarashevich.dto.mapper.UserReadMapper;
import by.dzmitry.yarashevich.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceDto {
    private UserRepository userRepository;
    private UserReadMapper userReadMapper;
    private UserEditMapper userEditMapper;

    public UserServiceDto(UserRepository userRepository, UserReadMapper userReadMapper, UserEditMapper userEditMapper) {
        this.userRepository = userRepository;
        this.userReadMapper = userReadMapper;
        this.userEditMapper = userEditMapper;
    }

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }

    public Optional<UserReadDto> findById(Integer id) {
        return userRepository.findById(id)
                .map(userReadMapper::map);
    }

    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(userEditMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    public Optional<UserReadDto> update(Integer id, UserCreateEditDto userDto) {
        return userRepository.findById(id)
                .map(entity -> userEditMapper.map(userDto, entity))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::map);
    }

    public boolean delete(Integer id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}

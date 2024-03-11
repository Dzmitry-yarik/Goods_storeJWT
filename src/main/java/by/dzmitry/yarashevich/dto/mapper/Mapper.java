package by.dzmitry.yarashevich.dto.mapper;

public interface Mapper <F, T> {
    T map(F object);
}

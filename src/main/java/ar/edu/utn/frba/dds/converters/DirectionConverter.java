package ar.edu.utn.frba.dds.converters;

import ar.edu.utn.frba.dds.entity.Direction;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DirectionConverter implements AttributeConverter<Direction, String> {
  @Override
  public String convertToDatabaseColumn(Direction direction) {
    String str = null;

    switch (direction) {
      case FORWARD:
        str = "IDA";
        break;
      case RETURN:
        str = "VUELTA";
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + direction);
    }
    return str;
  }

  @Override
  public Direction convertToEntityAttribute(String str) {
    Direction obj = null;

    switch (str) {
      case "IDA":
        obj = Direction.FORWARD;
        break;
      case "VUELTA":
        obj = Direction.RETURN;
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + str);
    }
    return obj;
  }
}

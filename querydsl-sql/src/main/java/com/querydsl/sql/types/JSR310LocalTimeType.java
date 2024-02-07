package com.querydsl.sql.types;

import java.sql.*;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import org.jetbrains.annotations.Nullable;

/**
 * JSR310LocalTimeType maps {@linkplain java.time.LocalTime} to {@linkplain java.sql.Time} on the
 * JDBC level
 */
public class JSR310LocalTimeType extends AbstractJSR310DateTimeType<LocalTime> {

  public JSR310LocalTimeType() {
    super(Types.TIME);
  }

  public JSR310LocalTimeType(int type) {
    super(type);
  }

  @Override
  public String getLiteral(LocalTime value) {
    return timeFormatter.format(value);
  }

  @Override
  public Class<LocalTime> getReturnedClass() {
    return LocalTime.class;
  }

  @Nullable
  @Override
  public LocalTime getValue(ResultSet rs, int startIndex) throws SQLException {
    Time time = rs.getTime(startIndex, utc());
    return time != null ? LocalTime.ofNanoOfDay(time.getTime() * 1000000) : null;
  }

  @Override
  public void setValue(PreparedStatement st, int startIndex, LocalTime value) throws SQLException {
    st.setTime(startIndex, new Time(value.get(ChronoField.MILLI_OF_DAY)), utc());
  }
}

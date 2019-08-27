package com.scatler.rrweb.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationTimeTableWrapper implements Serializable {
    private List<StationTimeTable> list;
}

package net.enderboy500.bellum.util;

import net.enderboy500.bellum.client.event.OutlineEntityEvent;
import org.jetbrains.annotations.Nullable;

public interface OutlineDataAttachment {
    OutlineEntityEvent.@Nullable OutlineData getOutlineData();
}
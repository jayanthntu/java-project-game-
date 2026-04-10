package action;

import ui.Describable;

public interface SpecialSkill extends Describable, Action {
    SpecialSkill copy();
}
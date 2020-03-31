import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import icons.ActionBasicsIcons;
import org.jetbrains.annotations.NotNull;

/**
 * Demonstrates adding an action group to a menu statically in plugin.xml, and then creating a menu item
 * within the group at runtime. See plugin.xml for the declaration of DynamicActionGroup,
 * and note the group declaration does not contain an action.
 * DynamicActionGroup is based on ActionGroup because menu children are determined
 * on rules other than just positional constraints.
 *
 * @author Anna Bulenkova
 * @see ActionGroup
 */
public class DynamicActionGroup extends ActionGroup {

    /**
     * Returns an array of menu actions for the group.
     *
     * @param  e Event received when the associated group-id menu is chosen.
     * @return AnAction[]  An instance of AnAction, in this case containing a single instance of the
     * PopupDialogAction class.
     */
    @NotNull
    @Override
    public AnAction[] getChildren(AnActionEvent e) {
        return new AnAction[]{ new PopupDialogAction("Action Added at Runtime",
                "Dynamic Action Demo",
                ActionBasicsIcons.Sdk_default_icon)
        };
    }

}
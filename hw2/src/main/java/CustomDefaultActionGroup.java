import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.editor.Editor;
import icons.ActionBasicsIcons;

/**
 * Creates an action group to contain menu actions. See plugin.xml declarations.
 * @author Anna Bulenkova
 * @author jhake
 */
public class CustomDefaultActionGroup extends DefaultActionGroup {

    /**
     * Given CustomDefaultActionGroup is derived from ActionGroup, in this context
     * update() determines whether the action group itself should be enabled or disabled.
     * Requires an editor to be active in order to enable the group functionality.
     * @see com.intellij.openapi.actionSystem.AnAction#update(AnActionEvent)
     * @param event  Event received when the associated group-id menu is chosen.
     */
    @Override
    public void update(AnActionEvent event) {
        // Enable/disable depending on whether user is editing
        Editor editor = event.getData(CommonDataKeys.EDITOR);
        event.getPresentation().setEnabled(editor != null);
        // Always make visible.
        event.getPresentation().setVisible(true);
        // Take this opportunity to set an icon for the menu entry.
        event.getPresentation().setIcon(ActionBasicsIcons.Sdk_default_icon);
    }
}
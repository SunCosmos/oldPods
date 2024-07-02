package bsh.util;

import bsh.NameSource;
import bsh.StringUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class NameCompletionTable extends ArrayList implements NameCompletion {
    List sources;
    NameCompletionTable table;

    public void add(NameSource nameSource) {
        if (this.sources == null) {
            this.sources = new ArrayList();
        }
        this.sources.add(nameSource);
    }

    public void add(NameCompletionTable nameCompletionTable) {
        if (this.table != null) {
            throw new RuntimeException("Unimplemented usage error");
        }
        this.table = nameCompletionTable;
    }

    @Override // bsh.util.NameCompletion
    public String[] completeName(String str) {
        ArrayList arrayList = new ArrayList();
        getMatchingNames(str, arrayList);
        if (arrayList.size() == 0) {
            return new String[0];
        }
        String str2 = (String) arrayList.get(0);
        for (int i2 = 1; i2 < arrayList.size() && str2.length() > 0; i2++) {
            str2 = StringUtil.maxCommonPrefix(str2, (String) arrayList.get(i2));
            if (str2.equals(str)) {
                break;
            }
        }
        return str2.length() > str.length() ? new String[]{str2} : (String[]) arrayList.toArray(new String[0]);
    }

    protected void getMatchingNames(String str, List list) {
        for (int i2 = 0; i2 < size(); i2++) {
            String str2 = (String) get(i2);
            if (str2.startsWith(str)) {
                list.add(str2);
            }
        }
        NameCompletionTable nameCompletionTable = this.table;
        if (nameCompletionTable != null) {
            nameCompletionTable.getMatchingNames(str, list);
        }
        if (this.sources != null) {
            for (int i3 = 0; i3 < this.sources.size(); i3++) {
                String[] allNames = ((NameSource) this.sources.get(i3)).getAllNames();
                for (int i4 = 0; i4 < allNames.length; i4++) {
                    if (allNames[i4].startsWith(str)) {
                        list.add(allNames[i4]);
                    }
                }
            }
        }
    }
}

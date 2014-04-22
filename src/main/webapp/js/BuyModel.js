var BuyModel = Ext.create('Ext.form.Panel', {
	title : '购买激活产品',
	labelAlign : 'left',
	buttonAlign : 'center',
	bodyStyle : 'padding:5px',
	bodyBorder : false,
	frame : true, //圆角和浅蓝色背景
	labelWidth : 100,
	width : 600,
	items : [{
		xtype : 'fieldset',
		title : '机器码',
		collapsible : false,

		items : [{
			xtype : 'textfield',
			anchor : '95%',
			cls : 'attr',
			name : 'model.name',
			value : 'xxxxxxxxxxxxxxxxxxxx',
			fieldLabel : '您的机器码',
			readOnly : true
		}]
	}, {
		xtype : 'fieldset',
		title : '在线购买',
		collapsible : false,

		items : [{
			xtype : 'textfield',
			anchor : '95%',
			cls : 'attr',
			name : 'model.name',
			fieldLabel : '您的姓名',
			allowBlank : false,
			blankText : '您的姓名不能为空'
		}, {
			xtype : 'textfield',
			anchor : '95%',
			cls : 'attr',
			name : 'model.unit',
			fieldLabel : '您的单位',
			allowBlank : false,
			blankText : '您的单位不能为空'
		}, {
			xtype : 'numberfield',
			anchor : '95%',
			cls : 'attr',
			name : 'model.phone',
			fieldLabel : '您的手机',
			allowBlank : false,
			blankText : '您的手机不能为空',
			hideTrigger : true,
			keyNavEnabled : false,
			mouseWheelEnabled : false
		}]
	}, {
		xtype : 'fieldset',
		title : '离线购买',
		collapsible : false,
		items : [{
			xtype : 'label',
			html : '<div style="padding:10px">请将您的姓名、单位以及机器码发送到手机:18919193664，并拨打电话联系作者</div>'
		}]
	}, {
		xtype : 'fieldset',
		title : '激活产品',
		collapsible : false,

		items : [{
			xtype : 'textfield',
			anchor : '95%',
			cls : 'attr',
			id : 'licence',
			fieldLabel : '注册码'
		}]
	}],

	buttons : [{
		text : '购买',
		iconCls : 'save',
		iconAlign : 'right',
		scope : this,
		handler : function() {
			this.buy();
		}
	}, {
		text : '激活',
		iconCls : 'save',
		scope : this,
		handler : function() {
			this.active();
		}
	}, {
		text : '重置',
		iconCls : 'reset',
		scope : this,
		handler : function() {
			this.frm.form.reset();
		}
	}]
});

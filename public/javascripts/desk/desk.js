// ユーザModelを作成
var Sentence = Backbone.Model.extend({
    clear: function() { // 削除する場合はdestroyしてHTMLタグを消す
		var el = this.el;
		this.destroy({success: function() {
			$(el).remove();
		}, wait: true});
	}, url: function() {
		alert("debug3:" + this.id);
//		return '/api/sentences/' + this.id;
		return (this.id) ? '/api/sentences/' + this.id : '/api/sentences';
	}
});
// ユーザCollectionを定義
var SentenceList = Backbone.Collection.extend({
    model: Sentence,
	url: '/api/sentences'
});
var Sentences = new SentenceList;

// ユーザViewを定義
var SentenceView = Backbone.View.extend({
    tagName: 'tr' // 全体を囲うタグをtrとする
	, template: _.template($('#sentence-template').html()) // テンプレート
	, events: { // tr以下に作成されるタグのイベントと関数指定
		'dblclick .view'    : 'edit'
		, 'keypress .edit'  : 'updateOnEnter'
		, 'blur .edit'      : 'close'
		, 'click a.destroy' : 'clear'
	}, initialize: function() {
		this.model.bind('change', this.render, this);
		this.model.bind('destroy', this.remove, this);
	}, render: function() {
		this.$el.html(this.template(this.model.toJSON()));
		this.name = this.$('.edit[name=name]');
		this.email = this.$('.edit[name=email]');
		return this;
	}, close: function() {
		var name = this.name.val();
		var email = this.email.val();
		this.model.save({name: name, email: email});
		this.$el.removeClass('editing');
	}, edit: function() {
		this.$el.addClass('editing');
	}, updateOnEnter: function(e) {
		if (e.keyCode == 13) this.close();
	}, clear: function() {
		this.model.clear();
	}

});

// アプリViewを作成
var AppView = Backbone.View.extend({
	el: $('#sentenceapp')
	, initialize: function() {
		this.name = this.$("#new-name");
		this.email = this.$("#new-email");
		Sentences.bind('add', this.addOne, this);
		Sentences.bind('reset', this.addAll, this);
		Sentences.bind('all', this.render, this);
		Sentences.fetch();
	}, events: {
		'keypress #new-name': 'createOnEnter',
		'keypress #new-email': 'createOnEnter'
	}, addOne: function(sentence) {
		var view = new SentenceView({model: sentence});
		this.$('#sentence-list').append(view.render().el);
	}, addAll: function() {
		Sentences.each(this.addOne);
	}, createOnEnter: function(e) {
		if (e.keyCode != 13) return;
		if (!this.name.val()) return;
		Sentences.create({name: this.name.val(), email: this.email.val()});
		this.name.val('');
		this.email.val('');
	}
});

// Routerを定義
var AppRouter = Backbone.Router.extend({
	routes: { // アドレスに対応する処理関数の定義
		'help': 'help' // /help の場合は下のhelp関数を呼ぶ
		, '': 'sentence'   // / の場合は下のuser関数を呼ぶ
	}, help: function() {
		$('#sentenceapp').html($('#help').html());
	}, sentence: function() {
		$('#sentenceapp').html($('#sentence').html());
		Sentences.fetch();
	}
});
var app = new AppRouter();
Backbone.history.start({pushState: true});

var App = new AppView;
